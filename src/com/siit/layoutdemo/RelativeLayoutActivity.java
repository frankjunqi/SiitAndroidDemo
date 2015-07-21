package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * ��Բ���
 * 
 * 
 */
public class RelativeLayoutActivity extends PicassoSampleActivity {

	/**
	 * android:layout_toLeftOf ���� ���λ�������������
		android:layout_toRightOf ���� ���λ������������ҷ�
		android:layout_above ���� ���λ������������Ϸ�
		android:layout_below ���� ���λ������������·�
		android:layout_alignParentLeft ���� ����Ƿ���븸��������
		android:layout_alignParentRight ���� ����Ƿ����丸������Ҷ�
		android:layout_alignParentTop ���� ����Ƿ���븸����Ķ���
		android:layout_alignParentBottom ���� ����Ƿ���븸����ĵײ�
		android:layout_centerInParent ���� ����Ƿ�����ڸ��������
		android:layout_centerHorizontal ���� ����Ƿ�������
		android:layout_centerVertical ���� ����Ƿ�ֱ����
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_relativelayout);
	}
}
