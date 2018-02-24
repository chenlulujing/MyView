package com.san.os.myview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.san.os.myview.ui.activity.AnimationGuideActivity;
import com.san.os.myview.ui.activity.MeasureActivity;
import com.san.os.myview.ui.activity.MemoryDemoActivity;
import com.san.os.myview.ui.activity.TabViewActivity;
import com.san.os.myview.ui.activity.CanvasActivity;
import com.san.os.myview.ui.activity.FloatingActionMenuActivity;
import com.san.os.myview.ui.activity.LetterActivity;
import com.san.os.myview.ui.activity.MyTextActivity;
import com.san.os.myview.ui.activity.NavActivity;
import com.san.os.myview.ui.activity.RadioButtonActivity;
import com.san.os.myview.ui.activity.ViewPagerActivity;
import com.san.os.myview.ui.activity.VoteActivity;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {

    private SearchEditText mEditText;
    private Button mBtnUpdate;
    private Button mBtnHandler;
    private Button mBtnPop;
    private Button mBtnView;
    private Button mBtnParams;
    private Button mBtnLine;
    private Button mBtnDialog;
    private Button mBtnAnamation;
    private Button mBtnEventDisPath;
    private Button mBtnShare;
    private Button mChoseFiles;
    private Button mBtn_nav;
    private Button mBtn_canvas;
    private Button mBtn_list;
    private Button mBtn_Vote;
    private Button mBtn_letter;
    private Button mEx_contain;
    private Button mText;
    private Button mFloatingActionMenu;
    private Button mRadioButton;
    private Button mTabButton;
    private TextImageView mView;
    private Button mMemoryDemo;
    private Button mMeasure;
    private Button mTabViewBottomView;
    private Button mAnimation_guide;

    private int FILE_SELECT_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mEditText = (SearchEditText) findViewById(R.id.edittext);
        mBtnUpdate = (Button) findViewById(R.id.update);
        mBtnPop = (Button) findViewById(R.id.pop);
        mBtnHandler = (Button) findViewById(R.id.handler);
        mView = (TextImageView) findViewById(R.id.myview);
        mBtnView = (Button) findViewById(R.id.view);
        mBtnParams = (Button) findViewById(R.id.params);
        mBtnLine = (Button) findViewById(R.id.line);
        mBtnDialog = (Button) findViewById(R.id.dialog);
        mBtnAnamation = (Button) findViewById(R.id.animation);
        mBtnShare = (Button) findViewById(R.id.share_system);
        mBtnEventDisPath = (Button) findViewById(R.id.event_dispath);
        mChoseFiles = (Button) findViewById(R.id.chose_file);
        mBtn_nav = (Button) findViewById(R.id.bt_nav);
        mBtn_canvas = (Button) findViewById(R.id.canvas);
        mBtn_list = (Button) findViewById(R.id.list_insert);
        mBtn_Vote = (Button) findViewById(R.id.vote);
        mBtn_letter = (Button) findViewById(R.id.letter);
        mEx_contain = (Button) findViewById(R.id.ex_contain);
        mText = (Button) findViewById(R.id.mytext);
        mFloatingActionMenu = (Button) findViewById(R.id.floatingactionmenu);
        mRadioButton = (Button) findViewById(R.id.radiobutton);
        mTabButton = (Button) findViewById(R.id.tabview);
        mMemoryDemo = (Button) findViewById(R.id.memory_demo);
        mMeasure = (Button) findViewById(R.id.measure);
        mTabViewBottomView = (Button) findViewById(R.id.tabview_bottom);
        mAnimation_guide = (Button) findViewById(R.id.animation_guide);

        mBtnUpdate.setOnClickListener(this);
        mBtnHandler.setOnClickListener(this);
        mBtnPop.setOnClickListener(this);
        mBtnView.setOnClickListener(this);
        mBtnParams.setOnClickListener(this);
        mBtnLine.setOnClickListener(this);
        mBtnDialog.setOnClickListener(this);
        mBtnAnamation.setOnClickListener(this);
        mBtnEventDisPath.setOnClickListener(this);
        mBtnShare.setOnClickListener(this);
        mChoseFiles.setOnClickListener(this);
        mBtn_nav.setOnClickListener(this);
        mBtn_canvas.setOnClickListener(this);
        mBtn_Vote.setOnClickListener(this);
        mBtn_letter.setOnClickListener(this);
        mEx_contain.setOnClickListener(this);
        mText.setOnClickListener(this);
        mFloatingActionMenu.setOnClickListener(this);
        mRadioButton.setOnClickListener(this);
        mTabButton.setOnClickListener(this);
        mMemoryDemo.setOnClickListener(this);
        mMeasure.setOnClickListener(this);
        mTabViewBottomView.setOnClickListener(this);
        mAnimation_guide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.handler:
                Intent intent1 = new Intent(this, HandlerActivity.class);
                startActivity(intent1);
                break;
            case R.id.update:
                updateView();
                break;
            case R.id.pop:
                Intent intent2 = new Intent(this, PopActivity.class);
                startActivity(intent2);
                break;
            case R.id.view:
                Intent intent3 = new Intent(this, ViewoutActivity.class);
                startActivity(intent3);
                break;
            case R.id.params:
                Intent intent4 = new Intent(this, ParamsActivity.class);
                startActivity(intent4);
                break;
            case R.id.line:
                Intent intent5 = new Intent(this, LineActivity.class);
                startActivity(intent5);
                break;
            case R.id.dialog:
                showDialog();
                break;
            case R.id.animation:
                Intent intent6 = new Intent(this, AnimationActivity.class);
                startActivity(intent6);
                break;
            case R.id.event_dispath:
                Intent intent7 = new Intent(this, EventActivity.class);
                startActivity(intent7);
                break;
            case R.id.share_system:
//                Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送的属性
//                intent.setType("text/plain"); // 分享发送的数据类型
//                String msg = "推荐给大家";
//                intent.putExtra(Intent.EXTRA_TEXT, msg); // 分享的内容
//                startActivity(Intent.createChooser(intent, "选择分享"));// 目标应用选择对话框的标题


                // 遍历 SD 卡下 .png 文件通过微信分享
                File root = Environment.getExternalStorageDirectory();
//                File[] files = root.listFiles(new FileFilter() {
//
//                    @Override
//                    public boolean accept(File pathname) {
//                        if (pathname.getName().endsWith(".png"))
//                            return true;
//                        return false;
//                    }
//                });
                File[] files = new File[2];
                files[0] = new File("file://media/external/images/media/204666");
                files[1] = new File("file://media/external/images/media/204684");
                shareMultiplePictureToTimeLine(files);
                break;
            case R.id.chose_file:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                try {
                    startActivityForResult(Intent.createChooser(intent, "请选择一个要上传的文件"),
                            FILE_SELECT_CODE);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "请安装文件管理器", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case R.id.bt_nav:
                Intent intent8 = new Intent(this, NavActivity.class);
                startActivity(intent8);

                break;
            case R.id.canvas:
                Intent intent9 = new Intent(this, CanvasActivity.class);
                startActivity(intent9);
                break;
            case R.id.vote:
                Intent intent10 = new Intent(this, VoteActivity.class);
                startActivity(intent10);
            case R.id.letter:
                Intent intent11 = new Intent(this, LetterActivity.class);
                startActivity(intent11);
                break;
            case R.id.ex_contain:
                Intent intent12 = new Intent(this, ExContainActivity.class);
                startActivity(intent12);
                break;
            case R.id.mytext:
                Intent intent13 = new Intent(this, MyTextActivity.class);
                startActivity(intent13);
                break;
            case R.id.floatingactionmenu:
                Intent intent14 = new Intent(this, FloatingActionMenuActivity.class);
                startActivity(intent14);
                break;
            case R.id.radiobutton:
                Intent intent15 = new Intent(this, RadioButtonActivity.class);
                startActivity(intent15);
                break;
            case R.id.tabview:
                Intent intent16 = new Intent(this, TabViewActivity.class);
                startActivity(intent16);
                break;
            case R.id.memory_demo:
                Intent intent17 = new Intent(this, MemoryDemoActivity.class);
                startActivity(intent17);
                break;
            case R.id.viewpager:
                Intent intent18 = new Intent(this, ViewPagerActivity.class);
                startActivity(intent18);
                break;
            case R.id.measure:
                Intent intent19 = new Intent(this, MeasureActivity.class);
                startActivity(intent19);
                break;
            case R.id.tabview_bottom:
                Intent intent20 = new Intent(this, TabViewBottomActivity.class);
                startActivity(intent20);
                break;
            case R.id.animation_guide:
                Intent intent21 = new Intent(this, AnimationGuideActivity.class);
                startActivity(intent21);
                break;


        }

    }

    private void shareMultiplePictureToTimeLine(File... files) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
        intent.setType("image/*");
//        ArrayList<Uri> imageUris = new ArrayList<Uri>();
//        if (files != null) {
//            for (File f : files) {
//                imageUris.add(Uri.fromFile(f));
//            }
//        }
//        intent.putExtra("sms_body", "content");
        intent.putExtra("Kdescription", "content_content");
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, mImageUris);


//        Intent intent = new Intent();
//        ComponentName comp = new ComponentName("com.tencent.mm",
//                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
//        intent.setComponent(comp);
//        intent.setAction(Intent.ACTION_SEND_MULTIPLE);
//        intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        intent.setType("text/plain");

        startActivity(intent);
    }

    private void showDialog() {

        AlertDialog builder = new AlertDialog.Builder(MainActivity.this, R.style.dialog).create();
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View layout = inflater.inflate(R.layout.umeng_update_force_dialog, null);
        builder.setInverseBackgroundForced(true);
        builder.setCancelable(true);
        builder.setCanceledOnTouchOutside(true);
        builder.setView(layout);
        builder.show();
    }


    private void updateView() {
        String str = mEditText.getText().toString();
        int day = 0;
        Log.i("myview", "onclick");
        if (!TextUtils.isEmpty(str)) {
            try {
                day = Integer.parseInt(str);
                if (day <= TextImageView.TOTALDAYS) {
                    mView.update(day);
                } else {
                    mView.update(TextImageView.TOTALDAYS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    ArrayList<Uri> mImageUris = new ArrayList<Uri>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                mImageUris.add(uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
