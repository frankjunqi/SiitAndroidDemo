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

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.siit.picassoutil.Picasso.Priority;

/**
 * Action代表了一个具体的加载任务，主要用于图片加载后的结果回调，有两个抽象方法，complete和error，
 * 也就是当图片解析为bitmap后用户希望做什么。最简单的就是将bitmap设置给imageview，失败了就将错误通过回调通知到上层。
 * 
 * 
 * @param <T>
 */
abstract class Action<T> {
	static class RequestWeakReference<M> extends WeakReference<M> {
		final Action action;

		public RequestWeakReference(Action action, M referent,
				ReferenceQueue<? super M> q) {
			super(referent, q);
			this.action = action;
		}
	}

	final Picasso picasso;
	final Request request;
	final WeakReference<T> target;
	final boolean skipCache;
	final boolean noFade;
	final int errorResId;
	final Drawable errorDrawable;
	final String key;
	final Object tag;

	boolean willReplay;
	boolean cancelled;

	Action(Picasso picasso, T target, Request request, boolean skipCache,
			boolean noFade, int errorResId, Drawable errorDrawable, String key,
			Object tag) {
		this.picasso = picasso;
		this.request = request;
		this.target = target == null ? null : new RequestWeakReference<T>(this,
				target, picasso.referenceQueue);
		this.skipCache = skipCache;
		this.noFade = noFade;
		this.errorResId = errorResId;
		this.errorDrawable = errorDrawable;
		this.key = key;
		this.tag = (tag != null ? tag : this);
	}

	abstract void complete(Bitmap result, Picasso.LoadedFrom from);

	abstract void error();

	void cancel() {
		cancelled = true;
	}

	Request getRequest() {
		return request;
	}

	T getTarget() {
		return target == null ? null : target.get();
	}

	String getKey() {
		return key;
	}

	boolean isCancelled() {
		return cancelled;
	}

	boolean willReplay() {
		return willReplay;
	}

	Picasso getPicasso() {
		return picasso;
	}

	Priority getPriority() {
		return request.priority;
	}

	Object getTag() {
		return tag;
	}
}
