package com.siit.layoutdemo;

import android.os.Bundle;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;

/**
 * ��񲼾ֺ;��Բ���
 * 
 * @author kjh08490
 * 
 */
public class TableLayoutActivity extends PicassoSampleActivity {
	/**
	 * TableRow��LinearLayout�����࣬����android:orientation����ֵ��Ϊhorizontal����������android
	 * :layout_width��android:layout_height����ֵ��ΪMATCH_PARENT��WRAP_CONTENT��
	 * ����������Ԫ�ض��Ǻ������� �����ҿ��һ�µġ����������ʹ��ÿ��TableRow�����Ԫ�ض��൱�ڱ���еĵ�Ԫ��һ������TableRow�У�
	 * ��Ԫ�����Ϊ�գ����ǲ��ܿ��С�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_tablelayout);
	}
}
