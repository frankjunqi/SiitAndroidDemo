package com.siit.dialogdemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.siit.picassodemo.R;

/**
 * ��ʾ��Ϣ�Ի���
 * 
 * @author
 * 
 */
public class CommonShowInfoDialog extends Dialog implements OnClickListener {

	private Context context;
	private ShowInfoDialogListener listener;
	private TextView tv_dialog_content, tv_button_line, percentTV, tv_line;
	private LinearLayout ll_dialog_btn;
	private Button btn_dialog_left, btn_dialog_right;
	private ProgressBar progressBar;
	private LinearLayout progressBarLayout;

	private int vis = View.GONE;// ��ť�Ƿ�ɼ�
	private String content;// չʾ����
	private String strLeftBtn, strRightBtn;// ��ť��Ϣ
	private ImageButton imgbtn_guanbi;
	private int contentGravity = Gravity.LEFT; // ��ʾ�İ��Ķ��뷽ʽ Ĭ�������
	private boolean rightCancle = true;// �Ҽ���ť����Ƿ�رյ���
	private SpannableStringBuilder spannableContent;

	public CommonShowInfoDialog(Context context) {
		super(context, R.style.MessageBox);
		this.context = context;
	}

	public void setCloseBtnGone() {// ���ɼ�
		imgbtn_guanbi.setVisibility(View.INVISIBLE);
	}

	public CommonShowInfoDialog(Context context, int vis, String content,
			String strLeft, String strRight) {
		this(context);
		this.context = context;
		this.vis = vis;
		this.content = content;
		this.strLeftBtn = strLeft;
		this.strRightBtn = strRight;
	}

	/**
	 * �˷��� ����ʹ��SpannableStringBuilder�ķ�ʽ����Html.fromHtmlʵ�ֵ�Ч��
	 * 
	 * @param context
	 * @param vis
	 * @param spannableContent
	 * @param strLeft
	 * @param strRight
	 */
	public CommonShowInfoDialog(Context context, int vis,
			SpannableStringBuilder spannableContent, String strLeft,
			String strRight) {
		this(context);
		this.context = context;
		this.vis = vis;
		this.spannableContent = spannableContent;
		this.strLeftBtn = strLeft;
		this.strRightBtn = strRight;
	}

	public CommonShowInfoDialog(Context context,
			ShowInfoDialogListener listener, int vis, String content,
			String strLeft, String strRight) {
		this(context);
		this.context = context;
		this.listener = listener;
		this.vis = vis;
		this.content = content;
		this.strLeftBtn = strLeft;
		this.strRightBtn = strRight;
	}

	/**
	 * �˷��� ����ʹ��SpannableStringBuilder�ķ�ʽ����Html.fromHtmlʵ�ֵ�Ч��
	 * 
	 * @param context
	 * @param vis
	 * @param spannableContent
	 * @param strLeft
	 * @param strRight
	 */
	public CommonShowInfoDialog(Context context,
			ShowInfoDialogListener listener, int vis,
			SpannableStringBuilder spannableContent, String strLeft,
			String strRight) {
		this(context);
		this.context = context;
		this.listener = listener;
		this.vis = vis;
		this.spannableContent = spannableContent;
		this.strLeftBtn = strLeft;
		this.strRightBtn = strRight;
	}

	public CommonShowInfoDialog(Context context,
			ShowInfoDialogListener listener, int vis, String content,
			String strRight) {
		this(context);
		this.context = context;
		this.listener = listener;
		this.vis = vis;
		this.content = content;
		this.strLeftBtn = "";
		this.strRightBtn = strRight;
	}

	/**
	 * �˷��� ����ʹ��SpannableStringBuilder�ķ�ʽ����Html.fromHtmlʵ�ֵ�Ч��
	 * 
	 * @param context
	 * @param vis
	 * @param spannableContent
	 * @param strLeft
	 * @param strRight
	 */
	public CommonShowInfoDialog(Context context,
			ShowInfoDialogListener listener, int vis,
			SpannableStringBuilder spannableContent, String strRight) {
		this(context);
		this.context = context;
		this.listener = listener;
		this.vis = vis;
		this.spannableContent = spannableContent;
		this.strLeftBtn = "";
		this.strRightBtn = strRight;
	}

	public void showdialog() {

		getWindow().setWindowAnimations(R.style.centerDialogWindowAnim);
		// ���ô��ڵ�������
		// setCanceledOnTouchOutside(true);
		WindowManager.LayoutParams wl = getWindow().getAttributes();
		wl.gravity = Gravity.CENTER;
		getWindow().setAttributes(wl);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		show();
	}

	// ��Ҫ������ʾ�İ��Ķ��䷽ʽ���������
	public void showdialog(int gravity) {
		contentGravity = gravity;
		getWindow().setWindowAnimations(R.style.centerDialogWindowAnim);
		// ���ô��ڵ�������
		// setCanceledOnTouchOutside(true);
		WindowManager.LayoutParams wl = getWindow().getAttributes();
		wl.gravity = Gravity.CENTER;
		getWindow().setAttributes(wl);
		getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		show();
	}

	public void showdialogWithoutClose() {
		showdialog();
		if (imgbtn_guanbi != null)
			imgbtn_guanbi.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_info_show);
		initUI();
	}

	private void initUI() {
		tv_dialog_content = (TextView) findViewById(R.id.tv_dialog_content);
		ll_dialog_btn = (LinearLayout) findViewById(R.id.ll_dialog_btn);
		tv_button_line = (TextView) findViewById(R.id.tv_button_line);
		tv_line = (TextView) findViewById(R.id.tv_line);
		btn_dialog_left = (Button) findViewById(R.id.btn_dialog_left);
		btn_dialog_right = (Button) findViewById(R.id.btn_dialog_right);
		progressBarLayout = (LinearLayout) findViewById(R.id.progress_layout);
		progressBar = (ProgressBar) findViewById(R.id.progressbar);
		percentTV = (TextView) findViewById(R.id.progress_percent);
		btn_dialog_left.setOnClickListener(this);
		btn_dialog_right.setOnClickListener(this);
		imgbtn_guanbi = (ImageButton) findViewById(R.id.imgbtn_guanbi);
		imgbtn_guanbi.setOnClickListener(this);

		if (!TextUtils.isEmpty(spannableContent)) {
			tv_dialog_content.setText(spannableContent);
		} else if (!TextUtils.isEmpty(content)) {
			tv_dialog_content.setText(content);
		}

		ll_dialog_btn.setVisibility(vis);
		if (vis == View.VISIBLE) {
			if ("".equals(strLeftBtn)) {
				btn_dialog_left.setVisibility(View.GONE);
				tv_button_line.setVisibility(View.GONE);
			} else
				btn_dialog_left.setText(strLeftBtn);

			btn_dialog_right.setText(strRightBtn);
			// ��button��ť��ʱ�����عرհ�ť
			imgbtn_guanbi.setVisibility(View.GONE);
		} else {
			tv_line.setVisibility(View.GONE);
		}
		setContentGravity(contentGravity);
	}

	@Override
	public void onClick(View v) {
		if (btn_dialog_left == v) {
			if (listener != null) {
				listener.refreshUI(ShowInfoDialogListener.BTN_LEFT);
			}
		} else if (btn_dialog_right == v) {
			if (listener != null) {
				listener.refreshUI(ShowInfoDialogListener.BTN_RIGHT);
			}
			if (isRightCancle())
				cancel();
			else
				return;
		} else if (imgbtn_guanbi == v) {
			if (listener != null) {
				listener.refreshUI(ShowInfoDialogListener.BTN_CANCEL);
			}
		}
		cancel();
	}

	/**
	 * ������ʾ���ݵĶ��뷽ʽ
	 * 
	 * @param gravity
	 */
	public void setContentGravity(int gravity) {
		tv_dialog_content.setGravity(gravity);
	}

	public ShowInfoDialogListener getListener() {
		return listener;
	}

	public void setListener(ShowInfoDialogListener listener) {
		this.listener = listener;
	}

	public boolean isRightCancle() {
		return rightCancle;
	}

	/**
	 * �����Ҽ���ť������Ƿ�رյ���
	 * 
	 * @param rightCancle
	 * <br/>
	 *            true ���ر� <br/>
	 *            false �����ر�
	 */
	public void setRightCancle(boolean rightCancle) {
		this.rightCancle = rightCancle;
	}

	/**
	 * ��ʾ������
	 */
	public void showProgress() {
		progressBarLayout.setVisibility(View.VISIBLE);
	}

	/**
	 * ���½�����
	 * 
	 * @param percent
	 */
	public void setProgress(int percent) {
		progressBar.setProgress(percent);
		percentTV.setText(percent + "%");
	}

	/**
	 * �����Ҽ����İ�
	 * 
	 * @param text
	 */
	public void setRightBtn(String text) {
		btn_dialog_right.setText(text);
	}

	/**
	 * �����Ҽ����İ�
	 * 
	 * @param text
	 */
	public void setLeftBtn(String text) {
		btn_dialog_left.setText(text);
	}

	public Button getBtn_dialog_right() {
		return btn_dialog_right;
	}

	public void setBtn_dialog_right(Button btn_dialog_right) {
		this.btn_dialog_right = btn_dialog_right;
	}

	public void setRightBtnTextColor(int id) {
		if (btn_dialog_right != null) {
			this.btn_dialog_right.setTextColor(context.getResources().getColor(
					id));
		}
	}

}
