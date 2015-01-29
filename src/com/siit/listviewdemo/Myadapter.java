package com.siit.listviewdemo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Myadapter extends BaseAdapter {

	@Override
	public int getCount() {
		// 数据源的数目
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// 返回具体某一项的对象
		return null;
	}

	@Override
	public long getItemId(int position) {
		// 返回item的自定义索引
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 具体每一项的view
		return null;
	}

}
