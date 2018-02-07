package com.san.os.myview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;

/**
 * @author luluc@yiche.com
 * @Description
 * @date 2017-04-17 08:36
 */

public class TabFragment extends Fragment {
    public static final String tag_str = "str";

    private String mTag;

    public static final Fragment newInstance(String str) {
        Fragment fragment = new TabFragment();
        Bundle bundle = new Bundle();
        bundle.putString(tag_str, str);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTag = getArguments().getString(tag_str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv = new TextView(getActivity());
//        tv.setText(mTag);
        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(100, 100);
//        PreViewTimeText tv = new PreViewTimeText(getActivity());
//        linearLayout.addView(tv, llp);
//        tv.setText(mTag);
//        return linearLayout;
//        return inflater.inflate(R.layout.view_pre_live, container, false);
        RadioButton radioButton = (RadioButton)LayoutInflater.from(container.getContext()).inflate(R.layout.myradiobutton,null);

        radioButton.setText("说车");
        relativeLayout.addView(radioButton, llp);

        View view = new View(getActivity());
        view.setBackgroundDrawable(ToolBox.getResources().getDrawable(R.drawable.skin_drawable_gradient_l2r_white));
        relativeLayout.addView(view,llp);

        return relativeLayout;
    }
}
