package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * ���Բ���
 * 
 * @author kjh08490
 * 
 */
public class AbsoluteLayoutActivity extends PicassoSampleActivity {
	/**
	 * AbsoluteLayout�Ǿ���λ�ò��֡��ڴ˲����е���Ԫ�ص�android:layout_x��android:layout_y���Խ���Ч��
	 * ������������Ԫ�ص�����λ�� ����Ļ���Ͻ�Ϊ����ԭ�㣨0,0������һ��0��������꣬�����ƶ���ֵ���󣬵ڶ���0���������꣬�����ƶ�����ֵ����
	 * �ڴ˲����е���Ԫ�ؿ����໥�ص� ����ʵ�ʿ����У�ͨ�������ô˲��ָ�ʽ����Ϊ���Ľ��������ڸ��ԣ��������п��ܲ��ܺܺõ���������նˡ�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_absolutelayout);
	}
}
