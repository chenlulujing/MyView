package com.san.os.myview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.san.os.myview.R;
import com.san.os.myview.model.FilterItemModel;
import com.san.os.myview.tool.ToolBox;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


public class DropDownMenu extends LinearLayout {

    //底部容器，包含popupMenuViews，maskView
    private FrameLayout containerView;
    private LinearLayout mPopuMenuRootView;
    //遮罩半透明View，点击可关闭DropDownMenu
    private View mShadowView;
    //是否下拉
    private boolean mIsShow;

    //分割线颜色
    private int dividerColor = 0xffcccccc;
    //tab选中颜色
    private int textSelectedColor = 0xff890c85;
    //tab未选中颜色
    private int textUnselectedColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //tab字体大小
    private int menuTextSize = 14;

    //tab选中图标
    private int menuSelectedIcon;
    //tab未选中图标
    private int menuUnselectedIcon;

    private float menuHeighPercent = 0.5f;


    public DropDownMenu(Context context) {
        super(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setOrientation(VERTICAL);

        //为DropDownMenu添加自定义属性
        int menuBackgroundColor = 0xffffffff;
        int underlineColor = 0xffcccccc;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        underlineColor = a.getColor(R.styleable.DropDownMenu_ddunderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.DropDownMenu_dddividerColor, dividerColor);
        textSelectedColor = a.getColor(R.styleable.DropDownMenu_ddtextSelectedColor, textSelectedColor);
        textUnselectedColor = a.getColor(R.styleable.DropDownMenu_ddtextUnselectedColor, textUnselectedColor);
        maskColor = a.getColor(R.styleable.DropDownMenu_ddmaskColor, maskColor);
        menuTextSize = a.getDimensionPixelSize(R.styleable.DropDownMenu_ddmenuTextSize, menuTextSize);
        menuSelectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuSelectedIcon, menuSelectedIcon);
        menuUnselectedIcon = a.getResourceId(R.styleable.DropDownMenu_ddmenuUnselectedIcon, menuUnselectedIcon);
        menuHeighPercent = a.getFloat(R.styleable.DropDownMenu_ddmenuMenuHeightPercent, menuHeighPercent);
        a.recycle();

        //为tabMenuView添加下划线
        View underLine = new View(getContext());
        underLine.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dpTpPx(1.0f)));
        underLine.setBackgroundColor(underlineColor);
        addView(underLine);

        //初始化containerView并将其添加到DropDownMenu
        containerView = new FrameLayout(context);
        containerView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        addView(containerView);

    }

    public void setDropDownMenu(final List<FilterItemModel> items) {
        if (items == null || items.size() == 0) {
            return;
        }

        //添加阴影
        mShadowView = new View(getContext());
        mShadowView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        mShadowView.setBackgroundColor(maskColor);
        mShadowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu();
            }
        });
        containerView.addView(mShadowView, 0);
        mShadowView.setVisibility(GONE);


        //添加菜单选项
        mPopuMenuRootView = new LinearLayout(getContext());
        mPopuMenuRootView.setVisibility(GONE);
        mPopuMenuRootView.setBackgroundColor(ToolBox.getResources().getColor(R.color.white));
        mPopuMenuRootView.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0, size = items.size(); i < size; i++) {
            ItemView tv = new ItemView(getContext(), items.get(i), i, mObserver);
            mPopuMenuRootView.addView(tv);
        }
        if (containerView.getChildAt(1) != null) {
            containerView.removeViewAt(1);
        }
        mPopuMenuRootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        containerView.addView(mPopuMenuRootView);
    }


    /**
     * 改变tab文字
     *
     * @param text
     */
    public void setTabText(String text) {

    }


    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (mIsShow) {
            mPopuMenuRootView.setVisibility(View.GONE);
            mPopuMenuRootView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            mShadowView.setVisibility(GONE);
            mShadowView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            mIsShow = false;
        }
    }

    private void showMenu() {
        if (!mIsShow) {
            mPopuMenuRootView.setVisibility(View.VISIBLE);
            mPopuMenuRootView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
            mShadowView.setVisibility(VISIBLE);
            mShadowView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
            mPopuMenuRootView.setVisibility(View.VISIBLE);
            mIsShow = true;
        }


    }

    /**
     * DropDownMenu是否处于可见状态
     *
     * @return
     */
    public boolean isShowing() {
        return mIsShow;
    }

    /**
     * 切换菜单
     */
    public void switchMenu() {
        if (mIsShow) {
            closeMenu();
        } else {
            showMenu();
        }
    }


    public int dpTpPx(float value) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, dm) + 0.5);
    }


    public void refresh(FilterItemModel item) {
        if (mPopuMenuRootView != null) {
            for (int i = 0, size = mPopuMenuRootView.getChildCount(); i < size; i++) {
                if(mPopuMenuRootView.getChildAt(i).getTag()!=null&&mPopuMenuRootView.getChildAt(i).getTag() instanceof FilterItemModel){
                    mPopuMenuRootView.getChildAt(i).setSelected(TextUtils.equals(((FilterItemModel) mPopuMenuRootView.getChildAt(i).getTag()).tagId,item.tagId));
                }else {
                    mPopuMenuRootView.getChildAt(i).setSelected(false);
                }

            }
        }
    }

    private Consumer mObserver;
    public void setObserver(Consumer consumer) {
        mObserver = consumer;
    }

    public void clearDownMenuItemStatus() {
        for (int i = 0, size = mPopuMenuRootView.getChildCount(); i < size; i++) {
            mPopuMenuRootView.getChildAt(i).setSelected(false);
        }
    }


    public static class ItemView extends RelativeLayout {


        private TextView mStrTv;
        private ImageView mCheckView;

        private Consumer mObserver;

        private FilterItemModel mFilterItemModel;

        public ItemView(Context context) {
            super(context);
            init(context);
        }

        public ItemView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        public ItemView(Context context, FilterItemModel data, int index, Consumer consumer) {
            super(context);
            init(context, data, index, consumer);
        }

        private void init(Context context) {

            setPadding(20, 20, 20, 20);
            mStrTv = new TextView(context);
            RelativeLayout.LayoutParams rlpStrTv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            rlpStrTv.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            addView(mStrTv, rlpStrTv);

            mCheckView = new ImageView(context);
            mCheckView.setVisibility(View.INVISIBLE);
            mCheckView.setBackgroundResource(R.drawable.filter_selected);
            RelativeLayout.LayoutParams rlpCheckView = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            rlpCheckView.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            addView(mCheckView, rlpCheckView);

        }

        private void init(Context context, FilterItemModel data, int index, Consumer consumer) {

            mObserver = consumer;
            mFilterItemModel = data;
            setTag(data);
            setPadding(40, 40, 40, 40);
            mStrTv = new TextView(context);
            mStrTv.setText(data.desc);
            RelativeLayout.LayoutParams rlpStrTv = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            rlpStrTv.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            addView(mStrTv, rlpStrTv);

            mCheckView = new ImageView(context);
            mCheckView.setVisibility(View.INVISIBLE);
            mCheckView.setBackgroundResource(R.drawable.filter_selected);
            RelativeLayout.LayoutParams rlpCheckView = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rlpCheckView.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            addView(mCheckView, rlpCheckView);

            setSelected(false);
            setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setSelected(!v.isSelected());
                    mFilterItemModel.mIsSelected = v.isSelected();
                    Observable.just(mFilterItemModel).subscribe(mObserver);
                }
            });

        }


        @Override
        public void setSelected(boolean selected) {
            super.setSelected(selected);
            mCheckView.setVisibility(selected ? VISIBLE : INVISIBLE);
        }
    }

}
