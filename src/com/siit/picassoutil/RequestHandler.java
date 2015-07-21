/*
 * Copyright (C) 2014 Square, Inc.
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

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;

/**
 * {@link RequestHandler} allows you to extend Picasso to load images
 * in ways that are not supported by default in the library.
 * <p>
 * <h2>Usage</h2>
 * <p>{@link RequestHandler} must be subclassed to be used. You will have to
 * override two methods ({@link #canHandleRequest(Request)} and
 * {@link #load(Request)}) with your custom logic to load images.</p>
 *
 * <p>You should then register your {@link RequestHandler} using
 * {@link Picasso.Builder#addRequestHandler(RequestHandler)}</p>
 *
 * <b>NOTE:</b> This is a beta feature. The API is subject to change in a backwards
 * incompatible way at any time.这是一个测试版的特性。API必须改变在任何时候在向后不兼容的方式。
 *
 * @see Picasso.Builder#addRequestHandler(RequestHandler)
 */
/**
 * 允许您扩展毕加索加载图片不支持的方式,默认情况下lib库中。
 * 你需要继承RequestHandler，你必须复写2个放方法: canHandleRequest() & load()您的自定义逻辑加载图片。
 * 如果自定义加载图片器，那么你需要在 Picasso.Builder#addRequestHandler(RequestHandler)进行加入自定义的加载器；
 *
 */
public abstract class RequestHandler {
  /**
   * {@link Result} represents the result of a {@link #load(Request)} call in a
   * {@link RequestHandler}. 通过load()方法实例一个Result对象
   *
   * @see RequestHandler
   * @see #load(Request)
   */
  public static final class Result {
    private final Picasso.LoadedFrom loadedFrom;// 描述了图像从何处加载。
    private final Bitmap bitmap;
    private final int exifOrientation;

    public Result(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
      this(bitmap, loadedFrom, 0);
    }

    Result(Bitmap bitmap, Picasso.LoadedFrom loadedFrom, int exifOrientation) {
      this.bitmap = bitmap;
      this.loadedFrom = loadedFrom;
      this.exifOrientation = exifOrientation;
    }

    /**
     * Returns the resulting {@link Bitmap} generated
     * from a {@link #load(Request)} call.
     */
    public Bitmap getBitmap() {
      return bitmap;
    }

    /**
     * Returns the resulting {@link Picasso.LoadedFrom} generated
     * from a {@link #load(Request)} call.
     */
    public Picasso.LoadedFrom getLoadedFrom() {
      return loadedFrom;
    }

    /**
     * Returns the resulting EXIF orientation generated
     * from a {@link #load(Request)} call. This is only accessible
     * to built-in RequestHandlers.
     */
    int getExifOrientation() {
      return exifOrientation;
    }
  }

  /**
   * Whether or not this {@link RequestHandler} can handle a request with the
   * given {@link Request}.是否能处理这个请求
   */
  public abstract boolean canHandleRequest(Request data);

  /**
   * Loads an image for the given {@link Request}.加载一个Image
   *
   * @param data the {@link android.net.Uri} to load the image from.
   * @return A {@link Result} instance representing the result.
   */
  public abstract Result load(Request data) throws IOException;

  int getRetryCount() {
    return 0;
  }

  boolean shouldRetry(boolean airplaneMode, NetworkInfo info) {
    return false;
  }

  boolean supportsReplay() {
    return false;
  }

  /**
   * Lazily create {@link BitmapFactory.Options} based in given
   * {@link Request}, only instantiating them if needed.根据所给的Request进行懒创建BitmapFactory.Options对象，如果需要就实例他们。
   */
  static BitmapFactory.Options createBitmapOptions(Request data) {
    final boolean justBounds = data.hasSize();
    final boolean hasConfig = data.config != null;
		/*
		 * 加载和显示图片是很消耗内存的一件事，BitmapFactory.Options 类, 允许我们定义图片以何种方式如何读到内存，
		 * 这样虽然我们可以得到我们期望大小的ImageView 但是在执行BitmapFactory.decodeFile(path,
		 * options);时，并没有节约内存。要想节约内存，还需要用到BitmapFactory.Options这个类里的
		 * inSampleSize 这个成员变量。 我们可以根据图片实际的宽高和我们期望的宽高来计算得到这个值。
		 */
    BitmapFactory.Options options = null;
    if (justBounds || hasConfig) {
      options = new BitmapFactory.Options();
      options.inJustDecodeBounds = justBounds;//这一行让代码只解码图片的Bounds
      if (hasConfig) {
        options.inPreferredConfig = data.config;
      }
    }
    return options;
  }

  /**
   * 是否需要设置这个值，来节约内存
   * inSampleSize这个成员变量,我们可以根据图片实际的宽高和我们期望的宽高来计算得到这个值。
   * @param options
   * @return
   */
  static boolean requiresInSampleSize(BitmapFactory.Options options) {
    return options != null && options.inJustDecodeBounds;
  }

  /**
   * 根据高、宽、options和request中的数值来计算这个最优的inSampleSize值。
   * @param reqWidth
   * @param reqHeight
   * @param options
   * @param request
   */
  static void calculateInSampleSize(int reqWidth, int reqHeight, BitmapFactory.Options options,
      Request request) {
    calculateInSampleSize(reqWidth, reqHeight, options.outWidth, options.outHeight, options,
        request);
  }

  /**
   * 计算最优的inSampleSize算法
   * @param reqWidth
   * @param reqHeight
   * @param width
   * @param height
   * @param options
   * @param request
   */
  static void calculateInSampleSize(int reqWidth, int reqHeight, int width, int height,
      BitmapFactory.Options options, Request request) {
    int sampleSize = 1;
    if (height > reqHeight || width > reqWidth) {
      final int heightRatio;
      final int widthRatio;
      if (reqHeight == 0) {
        sampleSize = (int) Math.floor((float) width / (float) reqWidth);
      } else if (reqWidth == 0) {
        sampleSize = (int) Math.floor((float) height / (float) reqHeight);
      } else {
        heightRatio = (int) Math.floor((float) height / (float) reqHeight);
        widthRatio = (int) Math.floor((float) width / (float) reqWidth);
        sampleSize = request.centerInside
            ? Math.max(heightRatio, widthRatio)
            : Math.min(heightRatio, widthRatio);
      }
    }
    options.inSampleSize = sampleSize;
    options.inJustDecodeBounds = false;// 代码不解码图片的Bounds
  }
}
