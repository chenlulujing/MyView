package com.san.os.myview.ui.view;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.san.os.myview.R;
import com.san.os.myview.tool.ToolBox;


/**
 * @author luluc@yiche.com
 * @Description
 */
public class PublishMenu implements View.OnClickListener {
    private View iconTiche;
    private View iconTiezi;
    private View iconTiwen;
    private View iconToupiao;
    ImageView fabIconNew;

    TextView rlTV1;
    TextView rlTV2;
    TextView rlTV3;
    TextView rlTV4;

    public static final int INDEX_TICHE = 1;
    public static final int INDEX_TIEZI = 2;
    public static final int INDEX_TIWEN = 3;
    public static final int INDEX_TOUPIAO = 4;
    public static final int MENU_OPEN = 5;
    public static final int MENU_CLOSE = 6;


    private FloatingActionButton rightLowerButton;
    private FloatingActionMenu rightLowerMenu;

    private OnMenuClickListener mOnMenuClick;


    public void setOnMenuClick(OnMenuClickListener menuClick) {
        this.mOnMenuClick = menuClick;
    }

    public void init(Activity context) {

        int buttonSize = ToolBox.dip2px(56);
        int buttonMargin = ToolBox.dip2px(15);
        int buttonMarginBottom = ToolBox.dip2px(59);

        fabIconNew = new ImageView(context);

        fabIconNew.setImageDrawable(context.getResources().getDrawable(R.drawable.skin_drawable_publish_close));

        FloatingActionButton.LayoutParams starParams = new FloatingActionButton.LayoutParams(buttonSize, buttonSize);
        starParams.setMargins(buttonMargin,
                buttonMargin,
                buttonMargin,
                buttonMarginBottom);
        fabIconNew.setLayoutParams(starParams);

        rightLowerButton = new FloatingActionButton.Builder(context)
                .setContentView(fabIconNew)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .setBackgroundDrawable(context.getResources().getDrawable(R.drawable.skin_drawable_inner_bg))
                .setLayoutParams(starParams)
                .build();
        rightLowerButton.getBgView().setBackgroundDrawable(context.getResources().getDrawable(R.drawable.skin_drawable_outter_bg));
        rightLowerButton.setId(R.id.publish_btn);
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(context);
        int menuButtonSize = ToolBox.dip2px(48);
        int menuRadio = ToolBox.dip2px(88);
        int tipRadio = ToolBox.dip2px(55);

        Drawable bgDraw = context.getResources().getDrawable(R.color.transparent);

        FrameLayout.LayoutParams menuParams = new FrameLayout.LayoutParams(menuButtonSize, menuButtonSize);
        rLSubBuilder.setLayoutParams(menuParams);

        ImageView rlIcon1 = createMenuItem(context, R.drawable.ic_action_tiche);
        ImageView rlIcon2 = createMenuItem(context, R.drawable.ic_action_tiezi);
        ImageView rlIcon3 = createMenuItem(context, R.drawable.ic_action_tiwen);
        ImageView rlIcon4 = createMenuItem(context, R.drawable.ic_action_toupiao);

        rlTV1 = createTipsItem(context, "提车\n作业");
        rlTV2 = createTipsItem(context, "帖子");
        rlTV3 = createTipsItem(context, "提问");
        rlTV4 = createTipsItem(context, "\n投票");

        // Build the menu with default options: light theme, 90 degrees, 72dp radius.
        // Set 4 default SubActionButtons

        iconTiche = rLSubBuilder.setContentView(rlIcon1).setBackgroundDrawable(bgDraw).build();
        iconTiezi = rLSubBuilder.setContentView(rlIcon2).setBackgroundDrawable(bgDraw).build();
        iconTiwen = rLSubBuilder.setContentView(rlIcon3).setBackgroundDrawable(bgDraw).build();
        iconToupiao = rLSubBuilder.setContentView(rlIcon4).setBackgroundDrawable(bgDraw).build();

        iconTiche.setId(R.id.publish_btn_tiche);
        iconTiezi.setId(R.id.publish_btn_zhuti);
        iconTiwen.setId(R.id.publish_btn_tiwen);
        iconToupiao.setId(R.id.publish_btn_toupiao);

        View tvTiche = rLSubBuilder.setContentView(rlTV1).setBackgroundDrawable(bgDraw).build();
        View tvTiezi = rLSubBuilder.setContentView(rlTV2).setBackgroundDrawable(bgDraw).build();
        View tvTiwen = rLSubBuilder.setContentView(rlTV3).setBackgroundDrawable(bgDraw).build();
        View tvToupiao = rLSubBuilder.setContentView(rlTV4).setBackgroundDrawable(bgDraw).build();

        rightLowerMenu = new FloatingActionMenu.Builder(context)
                .addSubActionView(menuRadio, iconTiezi)
                .addSubActionView(menuRadio, iconTiwen)
                .addSubActionView(menuRadio, iconTiche)
                .addSubActionView(menuRadio, iconToupiao)
                .addSubActionView(tipRadio, tvTiezi)
                .addSubActionView(tipRadio, tvTiwen)
                .addSubActionView(tipRadio, tvTiche)
                .addSubActionView(tipRadio, tvToupiao)
                .setAngle(menuRadio, 270, 145)
                .setAngle(tipRadio, 270, 125)
                .attachTo(rightLowerButton)
                .build();

        if (rightLowerButton.getGlobleBgView() != null) {
            rightLowerButton.getGlobleBgView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rightLowerMenu != null) {
                        rightLowerMenu.close(true, false);
                    }
                }
            });
        }

        // Listen menu open and close events to animate the button content view
        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu, boolean fromClick) {
                if (fromClick && mOnMenuClick != null) {
                    mOnMenuClick.onMenuClick(MENU_OPEN);
                }

                // Rotate the icon of rightLowerButton 45 degrees clockwise
                fabIconNew.setRotation(0);

                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();

                View globleBgView = rightLowerButton.getGlobleBgView();
                if (globleBgView != null) {
                    globleBgView.setVisibility(View.VISIBLE);
                }

                View bgView = rightLowerButton.getBgView();
                if (bgView != null) {
                    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4.5f);
                    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4.5f);
                    ObjectAnimator animationSX = ObjectAnimator.ofPropertyValuesHolder(bgView, pvhSX, pvhSY);
                    animationSX.start();
                }
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu, boolean fromClick) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                if (fromClick && mOnMenuClick != null) {
                    mOnMenuClick.onMenuClick(MENU_CLOSE);
                }
                fabIconNew.setRotation(45);

                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(fabIconNew, pvhR);
                animation.start();

                View globleBgView = rightLowerButton.getGlobleBgView();
                if (globleBgView != null) {
                    globleBgView.setVisibility(View.GONE);
                }

                View bgView = rightLowerButton.getBgView();
                if (bgView != null) {
                    PropertyValuesHolder pvhSX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f);
                    PropertyValuesHolder pvhSY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f);
                    ObjectAnimator animationSX = ObjectAnimator.ofPropertyValuesHolder(bgView, pvhSX, pvhSY);
                    animationSX.start();
                }
            }
        });


        iconTiche.setOnClickListener(this);
        iconTiezi.setOnClickListener(this);
        iconTiwen.setOnClickListener(this);
        iconToupiao.setOnClickListener(this);
    }

    public void clear() {
        this.mOnMenuClick = null;
    }

    public ImageView createMenuItem(Context c, int drawableId) {
        ImageView iv = (ImageView) ToolBox.inflate(c, R.layout.publish_iv_item, null, false);
        iv.setImageDrawable(c.getResources().getDrawable(drawableId));
        return iv;
    }

    public TextView createTipsItem(Context c, String text) {
        TextView iv = (TextView) ToolBox.inflate(c, R.layout.publish_tv_item, null, false);
        iv.setText(text);
        iv.setTextSize(TypedValue.COMPLEX_UNIT_PX, ToolBox.dip2px(c, 14));
        return iv;
    }

    public void setButtonVisibility(int visibility) {
        if (rightLowerButton != null) {
            rightLowerButton.setVisibility(visibility);
            if (rightLowerButton.getBgView() != null) {
                rightLowerButton.getBgView().setVisibility(visibility);
            }
        }
        if (visibility == View.GONE && rightLowerMenu != null && rightLowerMenu.isOpen()) {
            rightLowerMenu.close(false, false);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == iconTiche) {
            menuClick(INDEX_TICHE);
        } else if (v == iconTiezi) {
            menuClick(INDEX_TIEZI);
        } else if (v == iconTiwen) {
            menuClick(INDEX_TIWEN);
        } else if (v == iconToupiao) {
            menuClick(INDEX_TOUPIAO);
        }
    }

    private void menuClick(int index) {
        if (this.mOnMenuClick != null) {
            this.mOnMenuClick.onMenuClick(index);
        }
        if (rightLowerMenu != null) {
            rightLowerMenu.close(false, false);
        }
    }

    public interface OnMenuClickListener {
        public void onMenuClick(int clickIndex);
    }
}
