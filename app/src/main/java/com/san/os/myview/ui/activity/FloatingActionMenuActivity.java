package com.san.os.myview.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

import com.san.os.myview.R;
import com.san.os.myview.ui.view.PublishMenu;

/**
 * @author luluc@yiche.com
 * @Description
 */
public class FloatingActionMenuActivity extends FragmentActivity {

    private PublishMenu mPublishMenu;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiyt_floating_action_menu);
        initPublishMenu();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }



    private void initPublishMenu() {
        mPublishMenu = new PublishMenu();
        mPublishMenu.init(this);
        mPublishMenu.setButtonVisibility(View.VISIBLE);
        mPublishMenu.setOnMenuClick(new PublishMenu.OnMenuClickListener() {
            @Override
            public void onMenuClick(int clickIndex) {
                switch (clickIndex) {
                    case PublishMenu.INDEX_TICHE:

                        break;
                    case PublishMenu.INDEX_TIEZI:
                        break;
                    case PublishMenu.INDEX_TIWEN:
                        break;
                    case PublishMenu.INDEX_TOUPIAO:
                        break;
                    case PublishMenu.MENU_OPEN:
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
