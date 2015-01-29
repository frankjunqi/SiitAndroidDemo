/*
 * Copyright (C) 2013 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.siit.picassoutil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An input stream wrapper that supports unlimited independent cursors for
 * marking and resetting. Each cursor is a token, and it's the caller's
 * responsibility to keep track of these.一个输入流包装器,支持无限的独立游标标记和重置。每个游标是一个令牌,跟踪这些调用者的责任。
 */
final class MarkableInputStream extends InputStream {
  private static final int DEFAULT_BUFFER_SIZE = 4096;

  private final InputStream in;

  private long offset;
  private long reset;
  private long limit;
  private long defaultMark = -1;

  public MarkableInputStream(InputStream in) {
    this(in, DEFAULT_BUFFER_SIZE);
  }

  public MarkableInputStream(InputStream in, int size) {
	  // 判断这个inputstream是否支持mark() & reset()方法，默认返回false
    if (!in.markSupported()) {
      in = new BufferedInputStream(in, size);//BufferedInputStream流默认返回true
    }
    this.in = in;
  }

  /** Marks this place in the stream so we can reset back to it later.标记这个流的某一个位置，可以使我们之后能Reset到这个地方 */
  @Override public void mark(int readLimit) {
    defaultMark = savePosition(readLimit);
  }

  /**
   * Returns an opaque token representing the current position in the stream.返回一个不透明的令牌代表流中的当前位置。
   * Call {@link #reset(long)} to return to this position in the stream later.
   * It is an error to call {@link #reset(long)} after consuming more than
   * {@code readLimit} bytes from this stream.
   */
  public long savePosition(int readLimit) {
    long offsetLimit = offset + readLimit;
    if (limit < offsetLimit) {
      setLimit(offsetLimit);
    }
    return offset;
  }

  /**
   * Makes sure that the underlying stream can backtrack the full range from
   * {@code reset} thru {@code limit}. Since we can't call {@code mark()}
   * without also adjusting the reset-to-position on the underlying stream this
   * method resets first and then marks the union of the two byte ranges. On
   * buffered streams this additional cursor motion shouldn't result in any
   * additional I/O.
   */
  private void setLimit(long limit) {
    try {
      if (reset < offset && offset <= this.limit) {
        in.reset();
        in.mark((int) (limit - reset));
        skip(reset, offset);
      } else {
        reset = offset;
        in.mark((int) (limit - offset));
      }
      this.limit = limit;
    } catch (IOException e) {
      throw new IllegalStateException("Unable to mark: " + e);
    }
  }

  /** Resets the stream to the most recent {@link #mark mark}.重置mark最近的流 */
  @Override public void reset() throws IOException {
    reset(defaultMark);
  }

  /** Resets the stream to the position recorded by {@code token}.重置 流 到记录的位置 */
  public void reset(long token) throws IOException {
    if (offset > limit || token < reset) {
      throw new IOException("Cannot reset");
    }
    in.reset();
    skip(reset, token);
    offset = token;
  }

  /** Skips {@code target - current} bytes and returns. */
  private void skip(long current, long target) throws IOException {
    while (current < target) {
      long skipped = in.skip(target - current);
      if (skipped == 0) {
        if (read() == -1) {
          break; // EOF
        } else {
          skipped = 1;
        }
      }
      current += skipped;
    }
  }

  @Override public int read() throws IOException {
    int result = in.read();
    if (result != -1) {
      offset++;
    }
    return result;
  }

  @Override public int read(byte[] buffer) throws IOException {
    int count = in.read(buffer);
    if (count != -1) {
      offset += count;
    }
    return count;
  }

  @Override public int read(byte[] buffer, int offset, int length) throws IOException {
    int count = in.read(buffer, offset, length);
    if (count != -1) {
      this.offset += count;
    }
    return count;
  }

  @Override public long skip(long byteCount) throws IOException {
    long skipped = in.skip(byteCount);
    offset += skipped;
    return skipped;
  }

  @Override public int available() throws IOException {
    return in.available();
  }

  @Override public void close() throws IOException {
    in.close();
  }

  @Override public boolean markSupported() {
    return in.markSupported();
  }
}
