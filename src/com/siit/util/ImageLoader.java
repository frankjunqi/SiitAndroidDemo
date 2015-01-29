package com.siit.util;

/**
 * 图片加载管理类，上层业务和下层图片管理插件的中间
 * hl09287@ly.com
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.siit.picassodemo.R;
import com.siit.picassoutil.Callback;
import com.siit.picassoutil.Picasso;
import com.siit.picassoutil.Target;

public class ImageLoader {

	public static final int STUB_NULL = -1; // 不设置默认图

	private static final int STUB_ID = R.drawable.bg_home_ad_small; // 默认图片（再不设置默认图的情况下用这个）
	private static final Config DEFAULT_CONFIG = Config.RGB_565;

	private static ImageLoader imageLoader = null;
	private Context context;

	private ImageLoader(Context context) {
		this.context = context;
		context.getApplicationContext();
		// Picasso.with(context).setIndicatorsEnabled(true);
	}

	public static synchronized ImageLoader getInstance() {
		if (imageLoader == null) {
			imageLoader = new ImageLoader(PicassoApplication.getInstance());
		}
		return imageLoader;
	}

	/**
	 * 默认图片加载
	 * 
	 * @param url
	 * @param activity
	 * @param imageView
	 */
	public void displayImage(String imageUrl, ImageView imageView) {
		displayImage(imageUrl, imageView, STUB_ID, STUB_ID, DEFAULT_CONFIG);
	}

	/**
	 * 带默认图片的图片加载（加载成功前和加载失败后的图片一样）
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param stub_id
	 */
	public void displayImage(String imageUrl, ImageView imageView, int stub_id) {
		if (stub_id == STUB_NULL) {
			Picasso.with(context).load(imageUrl).config(DEFAULT_CONFIG)
					.into(imageView);
		} else {
			displayImage(imageUrl, imageView, stub_id, stub_id, DEFAULT_CONFIG);
		}
	}

	/**
	 * 带有设定图片品质的接
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param config
	 */
	public void displayImage(String imageUrl, ImageView imageView,
			Bitmap.Config config) {
		displayImage(imageUrl, imageView, STUB_ID, STUB_ID, config);
	}

	public void displayImage(String imageUrl, ImageView imageView, int stub_id,
			Bitmap.Config config) {
		displayImage(imageUrl, imageView, stub_id, stub_id, config);
	}

	/**
	 * 带默认图片的图片加载（加载成功前和加载失败后的图片显示）
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param stub_id
	 * @param stub_id_no_img
	 */
	public void displayImage(String imageUrl, ImageView imageView, int stub_id,
			int stub_id_no_img, Bitmap.Config config) {
		if (imageUrl == null || "".equals(imageUrl)) {
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(stub_id);
			return;
		}
		Picasso.with(context).load(imageUrl).placeholder(stub_id)
				.error(stub_id_no_img).config(config).into(imageView);
	}

	/**
	 * 带回调的图片加载（加载成功失败�?进度的回�?
	 * 
	 * @param imageUrl
	 * @param activity
	 * @param imageView
	 * @param pb_img_loading
	 * @param handler
	 */
	public void displayImage(String imageUrl, ImageView imageView,
			Callback callback) {
		displayImage(imageUrl, imageView, STUB_ID, STUB_ID, callback,
				DEFAULT_CONFIG);
	}

	public void displayImage(String imageUrl, ImageView imageView,
			Callback callback, int stub_id) {
		displayImage(imageUrl, imageView, stub_id, stub_id, callback,
				DEFAULT_CONFIG);
	}

	public void displayImage(String imageUrl, ImageView imageView,
			Callback callback, Config config) {
		displayImage(imageUrl, imageView, STUB_ID, STUB_ID, callback, config);
	}

	public void displayImage(String imageUrl, ImageView imageView,
			Callback callback, int stub_id, Config config) {
		displayImage(imageUrl, imageView, stub_id, stub_id, callback, config);
	}

	/**
	 * 带默认图片和回调函数的加
	 * 
	 * @param imageUrl
	 * @param imageView
	 * @param stub_id
	 * @param stub_id_no_img
	 * @param callback
	 */
	public void displayImage(String imageUrl, ImageView imageView, int stub_id,
			int stub_id_no_img, Callback callback, Config config) {
		if (imageUrl == null || "".equals(imageUrl)) {
			imageView.setScaleType(ScaleType.FIT_XY);
			imageView.setImageResource(stub_id);
			return;
		}
		Picasso.with(context).load(imageUrl).placeholder(stub_id)
				.error(stub_id_no_img).config(config).into(imageView, callback);

	}

	public void fetch(String imageUrl) {
		if (imageUrl == null || "".equals(imageUrl))
			return;
		Picasso.with(context).load(imageUrl).fit();
	}

	public void fetchToTarget(String imageUrl, Target target) {
		if (imageUrl == null || "".equals(imageUrl))
			return;
		Picasso.with(context).load(imageUrl).into(target);
	}

	/**
	 * 取消请求（用于释放资源）
	 * 
	 * @param view
	 */
	public void cancelRequest(ImageView view) {
		Picasso.with(context).cancelRequest(view);
	}
}
