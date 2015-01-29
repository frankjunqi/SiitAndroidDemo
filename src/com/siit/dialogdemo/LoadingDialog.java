package com.siit.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siit.picassodemo.R;

/**
 * loading
 * 
 * @author Tc11096
 * 
 */
public class LoadingDialog extends Dialog implements OnClickListener {

	private TextView topTextView;
	private ImageButton ib;
	private TextView tv_line;
	private String loadingText;
	private boolean flag = true;
	private int mTextMaxWidth = -1;
	private RelativeLayout rl_background;

	public LoadingDialog(Context context) {
		super(context, R.style.MessageLoadingBox);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_bar);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		initUI();
	}

	private void initUI() {
		topTextView = (TextView) findViewById(R.id.top_process_promot);
		rl_background = (RelativeLayout) findViewById(R.id.rl_background);
		ib = (ImageButton) findViewById(R.id.imgbtn_guanbi);
		tv_line = (TextView) findViewById(R.id.tv_line);
		if (mTextMaxWidth > 0) {
			topTextView.setMaxWidth(mTextMaxWidth);
		}
		ib.setOnClickListener(this);
		topTextView.setText(loadingText);
		showFlag();
	}

	public void setLoadingText(String text) {
		loadingText = text;
		if (loadingText.length() >= 12) {
			mTextMaxWidth = dip2px(getContext(), 125);
		} else {
			mTextMaxWidth = dip2px(getContext(), 95);
		}

		if (topTextView != null) {
			if (mTextMaxWidth > 0) {
				topTextView.setMaxWidth(mTextMaxWidth);
			}
			topTextView.setText(loadingText);
		}
	}

	public void showdialog() {
		show();
	}

	public void showFlag() {
		if (ib == null || rl_background == null) {
			return;
		}
		if (flag) {
			ib.setVisibility(View.VISIBLE);
			tv_line.setVisibility(View.VISIBLE);
		} else {
			ib.setVisibility(View.GONE);
			tv_line.setVisibility(View.GONE);
		}
	}

	@Override
	public void show() {
		showFlag();
		super.show();
	}

	@Override
	public void onClick(View v) {
		if (ib == v) {
			cancel();
		}
	}

	@Override
	public void setCancelable(boolean flag) {
		this.flag = flag;
		super.setCancelable(flag);
	}

	public boolean getDialogCancelable() {
		return flag;
	}

	public int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);// 小数点四舍五入取整
	}
}
