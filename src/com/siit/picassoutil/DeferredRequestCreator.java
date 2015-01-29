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

import java.lang.ref.WeakReference;

import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * ViewTreeObserver.OnPreDrawListener当一个视图树将要绘制时，所要调用的回调函数的接口类
 * 这是一个注册监听视图树的观察者(observer
 * )，在视图树种全局事件改变时得到通知。这个全局事件不仅还包括整个树的布局，从绘画过程开始，触摸模式的改变等。
 * ViewTreeObserver不能够被应用程序实例化，因为它是由视图提供，参照getViewTreeObserver()以查看更多信息。
 * 
 * @author kjh08490
 * 
 */
class DeferredRequestCreator implements ViewTreeObserver.OnPreDrawListener {

	final RequestCreator creator;
	final WeakReference<ImageView> target;
	Callback callback;

	// @TestOnly DeferredRequestCreator(RequestCreator creator, ImageView
	// target) {
	// this(creator, target, null);
	// }

	DeferredRequestCreator(RequestCreator creator, ImageView target,
			Callback callback) {
		this.creator = creator;
		this.target = new WeakReference<ImageView>(target);
		this.callback = callback;
		target.getViewTreeObserver().addOnPreDrawListener(this);
	}

	@Override
	public boolean onPreDraw() {// 为即将绘制ImageView视图树时执行的回调函数定义的接口.主要实现图片加载进来的动画效果
		ImageView target = this.target.get();
		if (target == null) {
			return true;
		}
		ViewTreeObserver vto = target.getViewTreeObserver();
		if (!vto.isAlive()) {
			return true;
		}

		int width = target.getWidth();
		int height = target.getHeight();

		if (width <= 0 || height <= 0) {
			return true;
		}

		vto.removeOnPreDrawListener(this);

		// 其中into方法中，setPlaceholder中会有AnimationDrawable来控制动画效果
		this.creator.unfit().resize(width, height).into(target, callback);
		return true;
	}

	void cancel() {
		callback = null;
		ImageView target = this.target.get();
		if (target == null) {
			return;
		}
		ViewTreeObserver vto = target.getViewTreeObserver();
		if (!vto.isAlive()) {
			return;
		}
		vto.removeOnPreDrawListener(this);
	}
}
