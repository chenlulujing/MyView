package com.san.os.myview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class ParamsActivity extends FragmentActivity {
    private RelativeLayout mRootContainer;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_params);
        RelativeLayout.LayoutParams Relparams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        mRootContainer = (RelativeLayout) findViewById(R.id.root);
//        View buttonView = LayoutInflater.from(this).inflate(R.layout.view_car_shequ_fragment_bottomview, mRootContainer, false);
        View buttonView = LayoutInflater.from(this).inflate(R.layout.view_car_shequ_fragment_bottomview, null, false);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) buttonView.getLayoutParams();
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        mRootContainer.addView(buttonView, params);

        Relparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        mRootContainer.addView(buttonView, Relparams);

        mTv = (TextView) findViewById(R.id.tv);
        RelativeLayout.LayoutParams tvParams  = (RelativeLayout.LayoutParams) mTv.getLayoutParams();
//        RelativeLayout.LayoutParams Relparams1 = new RelativeLayout.LayoutParams(200,200);
        tvParams.addRule(RelativeLayout.ABOVE,buttonView.getId());
        mTv.setLayoutParams(tvParams);
//        params.addRule(RelativeLayout.ABOVE, buttonView.getId());
//        mTv.setLayoutParams(params);
    }
}
