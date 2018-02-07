package com.san.os.myview.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.san.os.myview.R;


/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class Fragment_nav extends Fragment {

    private static final String TITLE = "title";

    private TextView mTextView;

    public static Fragment newInstance(String title) {
        Fragment_nav fragment = new Fragment_nav();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    public Fragment_nav() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = (TextView) getView().findViewById(R.id.title);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTextView.setText(arguments.getString(TITLE));
        }

    }
}
