package com.siit.dialogdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

public class DialogDemoActivity extends PicassoSampleActivity implements
		android.view.View.OnClickListener {

	private Button btn_one;
	private Button btn_two;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_demo);
		btn_one = (Button) findViewById(R.id.btn_one);
		btn_two = (Button) findViewById(R.id.btn_two);
		btn_one.setOnClickListener(this);
		btn_two.setOnClickListener(this);
	}

	/**
	 * 显示系统自带的dialog
	 */
	private void showDialog() {
		new AlertDialog.Builder(this).setTitle("Dialog Test")
				.setMessage("Dialog test .....")
				.setNegativeButton("取消", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogDemoActivity.this, "取消",
								Toast.LENGTH_LONG).show();
					}
				}).setPositiveButton("确定", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(DialogDemoActivity.this, "确定",
								Toast.LENGTH_LONG).show();
					}
				}).show();
	}

	/**
	 * 显示自定义的dialog
	 */
	private void showCustomDialog() {
		CommonShowInfoDialog dialog = new CommonShowInfoDialog(this,
				new ShowInfoDialogListener() {
					@Override
					public void refreshUI(String sType) {
						if (sType.equals(ShowInfoDialogListener.BTN_LEFT)) {
							Toast.makeText(DialogDemoActivity.this, "Left",
									Toast.LENGTH_LONG).show();
						} else if (sType
								.equals(ShowInfoDialogListener.BTN_RIGHT)) {
							Toast.makeText(DialogDemoActivity.this, "Right",
									Toast.LENGTH_LONG).show();
						}
					}
				}, View.VISIBLE, "自定义dialog的使用....", "Left Click",
				"Right Click");
		dialog.showdialog(Gravity.CENTER);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_one:
			showDialog();
			break;
		case R.id.btn_two:
			showCustomDialog();
			break;

		}
	}
}
