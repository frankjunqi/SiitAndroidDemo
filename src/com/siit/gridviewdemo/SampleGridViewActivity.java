package com.siit.gridviewdemo;

import com.siit.basedemoactivity.PicassoSampleActivity;
import com.siit.picassodemo.R;
import com.siit.picassodemo.R.id;
import com.siit.picassodemo.R.layout;
import com.siit.util.SampleScrollListener;

import android.os.Bundle;
import android.widget.GridView;

public class SampleGridViewActivity extends PicassoSampleActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sample_gridview_activity);

    GridView gv = (GridView) findViewById(R.id.grid_view);
    gv.setAdapter(new SampleGridViewAdapter(this));
    gv.setOnScrollListener(new SampleScrollListener(this));
  }
}
