package com.siit.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/** An image view which always remains square with respect to its width. */
public final class SquaredImageView extends ImageView {
	public SquaredImageView(Context context) {
		super(context);
	}

	public SquaredImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		// 获取父控件的高度和宽度
		setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setColor(Color.GREEN);// 设置画笔颜色
		paint.setStyle(Paint.Style.FILL);// 画笔为实心
		paint.setStrokeWidth(66);
		paint.setTextSize(50);
		// 画字(具体看Canvas中提供的绘制的方法)
		canvas.drawText("hello", 100, 100, paint);
	}
}
