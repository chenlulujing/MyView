package com.san.os.myview.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.ui.view.LettersView;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class LetterActivity extends FragmentActivity implements LettersView.OnSelectedLetterListener {

    private TextView mResult;
    private LettersView mLetterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actvity_letter);

        mResult = (TextView) findViewById(R.id.result);
        mLetterView = (LettersView) findViewById(R.id.letter);

        mLetterView.setOnSelectedLetterListener(this);
    }


    @Override
    public void onSelectedLetter(int index, String letter) {
        mResult.setText(index + ":" + letter);
    }
}
