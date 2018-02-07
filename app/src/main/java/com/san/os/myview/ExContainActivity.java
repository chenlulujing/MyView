package com.san.os.myview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import java.util.HashSet;

/**
 * @author luluc@yiche.com
 * @ClassName ${type_name}
 * @Description
 * @date ${date} ${time}
 */
public class ExContainActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_contain);
        (findViewById(R.id.start_int)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashSet<String> ids = new HashSet<String>();
                for (int i = 0; i < 20; i++) {
                    ids.add(i + "");
                }
                if (ids.contains(2)) {
                    Toast.makeText(ExContainActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExContainActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
        (findViewById(R.id.start_string)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashSet<String> ids = new HashSet<String>();
                for (int i = 0; i < 20; i++) {
                    ids.add(i + "");
                }
                if (ids.contains(2+"")) {
                    Toast.makeText(ExContainActivity.this, "true", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ExContainActivity.this, "false", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
