/*
 *   Copyright 2014 Oguz Bilgener
 */
package com.oguzdev.circularfloatingactionmenu.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.oguzdev.circularfloatingactionmenu.library.animation.DefaultAnimationHandler;
import com.oguzdev.circularfloatingactionmenu.library.animation.MenuAnimationHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides the main structure of the menu.
 */

public class FloatingActionMenu {

    /**
     * Reference to the view (usually a button) to trigger the menu to show
     */
    private View mainActionView;

    private ViewGroup mViewGroup;
//    /**
//     * The angle (in degrees, modulus 360) which the circular menu starts from
//     */
//    private int startAngle;
//    /**
//     * The angle (in degrees, modulus 360) which the circular menu ends at
//     */
//    private int endAngle;
//    /**
//     * Distance of menu items from mainActionView
//     */
//    private int radius;
    /**
     * List of menu items
     */
    private List<Item> subActionItems;

    private SparseArray<List<Item>> items;
    private SparseArray<Builder.Angle> angle;


    /**
     * Reference to the preferred {@link MenuAnimationHandler} object
     */
    private MenuAnimationHandler animationHandler;
    /**
     * Reference to a listener that listens open/close actions
     */
    private MenuStateChangeListener stateChangeListener;
    /**
     * whether the openings and closings should be animated or not
     */
    private boolean animated;
    /**
     * whether the menu is currently open or not
     */
    private boolean open;
//    /**
//     * whether the menu is an overlay for all other activities
//     */
//    private boolean systemOverlay;
    /**
     * a simple layout to contain all the sub action views in the system overlay mode
     */
    private FrameLayout overlayContainer;

    private OrientationEventListener orientationListener;

    /**
     * Constructor that takes the parameters collected using {@link FloatingActionMenu.Builder}
     *
     * @param mainActionView
     * @param animationHandler
     * @param animated
     */
    public FloatingActionMenu(final View mainActionView,
                              SparseArray<Builder.Angle> angle,
                              SparseArray<List<Item>> items,
                              MenuAnimationHandler animationHandler,
                              boolean animated,
                              MenuStateChangeListener stateChangeListener,
                              final boolean systemOverlay,
                              ViewGroup viewGroup) {
        this.mainActionView = mainActionView;
        this.mViewGroup = viewGroup;
        this.animationHandler = animationHandler;
        this.animated = animated;
        this.items = items;
        this.angle = angle;
        subActionItems = new ArrayList<>();
        // The menu is initially closed.
        this.open = false;

        this.stateChangeListener = stateChangeListener;

        // Listen click events on the main action view
        // In the future, touch and drag events could be listened to offer an alternative behaviour
        this.mainActionView.setClickable(true);
        this.mainActionView.setOnClickListener(new ActionViewClickListener());

        // Do not forget to set the menu as self to our customizable animation handler
        if (animationHandler != null) {
            animationHandler.setMenu(this);
        }

        // Find items with undefined sizes
        for (int i = 0, l = items.size(); i < l; i++) {
            List<Item> itemList = items.valueAt(i);
            for (final Item item : itemList) {
                if (item.width == 0 || item.height == 0) {
                    // Figure out the size by temporarily adding it to the Activity content view hierarchy
                    // and ask the size from the system
                    addViewToCurrentContainer(item.view);
                    // Make item view invisible, just in case
                    item.view.setAlpha(0);
                    // Wait for the right time
                    item.view.post(new ItemViewQueueListener(item));
                }
            }
        }
    }

    /**
     * Simply opens the menu by doing necessary calculations.
     *
     * @param animated if true, this action is executed by the current {@link MenuAnimationHandler}
     */
    public void open(boolean animated, boolean fromClick) {

        // Get the center of the action view from the following function for efficiency
        // populate destination x,y coordinates of Items
        Point center = getActionViewCenter();

        for (int i = 0, l = items.size(); i < l; i++) {
            calculateItemPositions(center, items.keyAt(i), items.valueAt(i));
        }

        if (animated && animationHandler != null) {
            // If animations are enabled and we have a MenuAnimationHandler, let it do the heavy work
            if (animationHandler.isAnimating()) {
                // Do not proceed if there is an animation currently going on.
                return;
            }

            for (int i = 0; i < subActionItems.size(); i++) {
                // It is required that these Item views are not currently added to any parent
                // Because they are supposed to be added to the Activity content view,
                // just before the animation starts
                if (subActionItems.get(i).view.getParent() != null) {
                    throw new RuntimeException("All of the sub action items have to be independent from a parent.");
                }

                // Initially, place all items right at the center of the main action view
                // Because they are supposed to start animating from that point.
                final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(subActionItems.get(i).width, subActionItems.get(i).height, Gravity.TOP | Gravity.LEFT);

                params.setMargins(center.x - subActionItems.get(i).width / 2, center.y - subActionItems.get(i).height / 2, 0, 0);
                addViewToCurrentContainer(subActionItems.get(i).view, params);
            }
            // Tell the current MenuAnimationHandler to animate from the center
            animationHandler.animateMenuOpening(center);
        } else {
            // If animations are disabled, just place each of the items to their calculated destination positions.
            for (int i = 0; i < subActionItems.size(); i++) {
                // This is currently done by giving them large margins

                final FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(subActionItems.get(i).width, subActionItems.get(i).height, Gravity.TOP | Gravity.LEFT);
                params.setMargins(subActionItems.get(i).x, subActionItems.get(i).y, 0, 0);
                subActionItems.get(i).view.setLayoutParams(params);
                // Because they are placed into the main content view of the Activity,
                // which is itself a FrameLayout
                addViewToCurrentContainer(subActionItems.get(i).view, params);
            }
        }
        // do not forget to specify that the menu is open.
        open = true;

        if (stateChangeListener != null) {
            stateChangeListener.onMenuOpened(this, fromClick);
        }

    }

    /**
     * Closes the menu.
     *
     * @param animated if true, this action is executed by the current {@link MenuAnimationHandler}
     */
    public void close(boolean animated, boolean fromClick) {
        // If animations are enabled and we have a MenuAnimationHandler, let it do the heavy work
        if (animated && animationHandler != null) {
            if (animationHandler.isAnimating()) {
                // Do not proceed if there is an animation currently going on.
                return;
            }
            animationHandler.animateMenuClosing(getActionViewCenter());
        } else {
            // If animations are disabled, just detach each of the Item views from the Activity content view.
            for (int i = 0; i < subActionItems.size(); i++) {
                removeViewFromCurrentContainer(subActionItems.get(i).view);
            }
            detachOverlayContainer();
        }
        // do not forget to specify that the menu is now closed.
        open = false;

        if (stateChangeListener != null) {
            stateChangeListener.onMenuClosed(this, fromClick);
        }
    }

    /**
     * Toggles the menu
     *
     * @param animated if true, the open/close action is executed by the current {@link MenuAnimationHandler}
     */
    public void toggle(boolean animated, boolean fromClick) {
        if (open) {
            close(animated, fromClick);
        } else {
            open(animated, fromClick);
        }
    }

    /**
     * @return whether the menu is open or not
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * @return whether the menu is a system overlay or not
     */
    public boolean isSystemOverlay() {
//        return systemOverlay;
        return false;
    }

    public FrameLayout getOverlayContainer() {
        return overlayContainer;
    }


    /**
     * Gets the coordinates of the main action view
     * This method should only be called after the main layout of the Activity is drawn,
     * such as when a user clicks the action button.
     *
     * @return a Point containing x and y coordinates of the top left corner of action view
     */
    private Point getActionViewCoordinates() {
        int[] coords = new int[2];
        // This method returns a x and y values that can be larger than the dimensions of the device screen.
        mainActionView.getLocationOnScreen(coords);

        // So, we need to deduce the offsets.
//        if (systemOverlay) {
//            coords[1] -= getStatusBarHeight();
//        } else {
        Rect activityFrame = new Rect();
        getActivityContentView().getWindowVisibleDisplayFrame(activityFrame);
        coords[0] -= (getScreenSize().x - getActivityContentView().getMeasuredWidth());
        coords[1] -= (activityFrame.height() + activityFrame.top - getActivityContentView().getMeasuredHeight());
//        }
        return new Point(coords[0], coords[1]);
    }

    /**
     * Returns the center point of the main action view
     *
     * @return the action view center point
     */
    public Point getActionViewCenter() {
        Point point = getActionViewCoordinates();
        point.x += mainActionView.getMeasuredWidth() / 2;
        point.y += mainActionView.getMeasuredHeight() / 2;
        return point;
    }

    /**
     * Calculates the desired positions of all items.
     *
     * @return getActionViewCenter()
     */
    private void calculateItemPositions(final Point center, int radius, List<Item> itemList) {
        // Create an arc that starts from startAngle and ends at endAngle
        // in an area that is as large as 4*radius^2
        RectF area = new RectF(center.x - radius, center.y - radius, center.x + radius, center.y + radius);
        Builder.Angle angle = this.angle.get(radius);
        int startAngle = angle.startAngle;
        int endAngle = angle.endAngle;
        Path orbit = new Path();
        orbit.addArc(area, startAngle, endAngle - startAngle);

        PathMeasure measure = new PathMeasure(orbit, false);

        // Prevent overlapping when it is a full circle
        int divisor;
        if (Math.abs(endAngle - startAngle) >= 360 || itemList.size() <= 1) {
            divisor = itemList.size();
        } else {
            divisor = itemList.size() - 1;
        }

        // Measure this path, in order to find points that have the same distance between each other
        for (int i = 0; i < itemList.size(); i++) {
            float[] coords = new float[]{0f, 0f};
            measure.getPosTan((i) * measure.getLength() / divisor, coords, null);
            // get the x and y values of these points and set them to each of sub action items.
            Item item = itemList.get(i);
            item.x = (int) coords[0] - item.width / 2;
            item.y = (int) coords[1] - item.height / 2;
            if (!subActionItems.contains(item)) {
                subActionItems.add(item);
            }
        }
    }

//    /**
//     * @return the specified raduis of the menu
//     */
//    public int getRadius() {
//        return radius;
//    }

    /**
     * @return a reference to the sub action items list
     */
    public List<Item> getSubActionItems() {
        return subActionItems;
    }

    /**
     * Finds and returns the main content view from the Activity context.
     *
     * @return the main content view
     */
    public View getActivityContentView() {
        try {
            if (mViewGroup != null) {
                return mViewGroup;
            } else
                return ((Activity) mainActionView.getContext()).getWindow().getDecorView().findViewById(android.R.id.content);
        } catch (ClassCastException e) {
            throw new ClassCastException("Please provide an Activity context for this FloatingActionMenu.");
        }
    }

    /**
     * Intended to use for systemOverlay mode.
     *
     * @return the WindowManager for the current context.
     */
    public WindowManager getWindowManager() {
        return (WindowManager) mainActionView.getContext().getSystemService(Context.WINDOW_SERVICE);
    }

    private void addViewToCurrentContainer(View view, ViewGroup.LayoutParams layoutParams) {
//        if (systemOverlay) {
//            overlayContainer.addView(view, layoutParams);
//        } else {
        try {
            if (layoutParams != null) {
                ViewGroup.LayoutParams lp = layoutParams;
                ((ViewGroup) getActivityContentView()).addView(view, lp);
            } else {
                ((ViewGroup) getActivityContentView()).addView(view);
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("layoutParams must be an instance of " +
                    "FrameLayout.LayoutParams.");
        }
//        }
    }

    public void detachOverlayContainer() {
        if (overlayContainer != null) {
            getWindowManager().removeView(overlayContainer);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = mainActionView.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mainActionView.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void addViewToCurrentContainer(View view) {
        addViewToCurrentContainer(view, null);
    }

    public void removeViewFromCurrentContainer(View view) {
//        if (systemOverlay) {
//            overlayContainer.removeView(view);
//        } else {
        if (mViewGroup != null) {
            mViewGroup.removeAllViews();
        } else {
            ((ViewGroup) getActivityContentView()).removeView(view);
        }
//        }
    }

    /**
     * Retrieves the screen size from the Activity context
     *
     * @return the screen size as a Point object
     */
    private Point getScreenSize() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }

    public void setStateChangeListener(MenuStateChangeListener listener) {
        this.stateChangeListener = listener;
    }

    /**
     * A simple click listener used by the main action view
     */
    public class ActionViewClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            toggle(animated,true);
        }
    }

    /**
     * This runnable calculates sizes of Item views that are added to the menu.
     */
    private class ItemViewQueueListener implements Runnable {

        private static final int MAX_TRIES = 10;
        private Item item;
        private int tries;

        public ItemViewQueueListener(Item item) {
            this.item = item;
            this.tries = 0;
        }

        @Override
        public void run() {
            // Wait until the the view can be measured but do not push too hard.
            if (item.view.getMeasuredWidth() == 0 && tries < MAX_TRIES) {
                item.view.post(this);
                return;
            }
            // Measure the size of the item view
            item.width = item.view.getMeasuredWidth();
            item.height = item.view.getMeasuredHeight();

            // Revert everything back to normal
            item.view.setAlpha(item.alpha);
            // Remove the item view from view hierarchy
            removeViewFromCurrentContainer(item.view);
        }
    }

    /**
     * A simple structure to put a view and its x, y, width and height values together
     */
    public static class Item {
        public int x;
        public int y;
        public int width;
        public int height;

        public float alpha;

        public View view;

        public Item(View view, int width, int height) {
            this.view = view;
            this.width = width;
            this.height = height;
            alpha = view.getAlpha();
            x = 0;
            y = 0;
        }
    }

    /**
     * A listener to listen open/closed state changes of the Menu
     */
    public static interface MenuStateChangeListener {
        public void onMenuOpened(FloatingActionMenu menu, boolean fromClick);

        public void onMenuClosed(FloatingActionMenu menu, boolean fromClick);
    }

    /**
     * A builder for {@link FloatingActionMenu} in conventional Java Builder format
     */
    public static class Builder {

        private int startAngle;
        private int endAngle;
        private int radius;
        private View actionView;
        private ViewGroup viewGroup;
        private SparseArray<List<Item>> items;
        private SparseArray<Angle> itemsAngle;
        //        private List<Item> subActionItems;
        private MenuAnimationHandler animationHandler;
        private boolean animated;
        private MenuStateChangeListener stateChangeListener;
        private boolean systemOverlay;

        public Builder(Context context, boolean systemOverlay) {
//            subActionItems = new ArrayList<Item>();
            items = new SparseArray<>();
            itemsAngle = new SparseArray<>();
            // Default settings
            radius = context.getResources().getDimensionPixelSize(R.dimen.action_menu_radius);
            startAngle = 180;
            endAngle = 270;
            animationHandler = new DefaultAnimationHandler();
            animated = true;
            this.systemOverlay = systemOverlay;
        }

        public static class Angle {
            public int startAngle;
            public int endAngle;

            public Angle(int s, int e) {
                this.startAngle = s;
                this.endAngle = e;
            }
        }

        public Builder(Context context) {
            this(context, false);
        }

        public Builder setAngle(int radius, int s, int e) {
            itemsAngle.put(radius, new Angle(s, e));
            return this;
        }

        public Builder addSubActionView(int radius, View subActionView, int width, int height) {
            List<Item> itemList = this.items.get(radius);
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
            itemList.add(new Item(subActionView, width, height));
            this.items.put(radius, itemList);
            return this;
        }

        public Builder setParent(ViewGroup viewGroup) {
            this.viewGroup = viewGroup;
            return this;
        }

        /**
         * Adds a sub action view that is already alive, but not added to a parent View.
         *
         * @param subActionView a view for the menu
         * @return the builder object itself
         */
        public Builder addSubActionView(int radius, View subActionView) {
            if (systemOverlay) {
                throw new RuntimeException("Sub action views cannot be added without " +
                        "definite width and height. Please use " +
                        "other methods named addSubActionView");
            }
            return this.addSubActionView(radius, subActionView, 0, 0);
        }

//        /**
//         * Inflates a new view from the specified resource id and adds it as a sub action view.
//         *
//         * @param resId   the resource id reference for the view
//         * @param context a valid context
//         * @return the builder object itself
//         */
//        public Builder addSubActionView(int resId, Context context) {
//            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View view = inflater.inflate(resId, null, false);
//            view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//            return this.addSubActionView(view, view.getMeasuredWidth(), view.getMeasuredHeight());
//        }

        /**
         * Sets the current animation handler to the specified MenuAnimationHandler child
         *
         * @param animationHandler a MenuAnimationHandler child
         * @return the builder object itself
         */
        public Builder setAnimationHandler(MenuAnimationHandler animationHandler) {
            this.animationHandler = animationHandler;
            return this;
        }

        public Builder enableAnimations() {
            animated = true;
            return this;
        }

        public Builder disableAnimations() {
            animated = false;
            return this;
        }

        public Builder setStateChangeListener(MenuStateChangeListener listener) {
            stateChangeListener = listener;
            return this;
        }

        public Builder setSystemOverlay(boolean systemOverlay) {
            this.systemOverlay = systemOverlay;
            return this;
        }

        /**
         * Attaches the whole menu around a main action view, usually a button.
         * All the calculations are made according to this action view.
         *
         * @param actionView
         * @return the builder object itself
         */
        public Builder attachTo(View actionView) {
            this.actionView = actionView;
            return this;
        }


        public FloatingActionMenu build() {
            return new FloatingActionMenu(actionView,
                    itemsAngle,
                    items,
                    animationHandler,
                    animated,
                    stateChangeListener,
                    systemOverlay, viewGroup);
        }
    }

    public static WindowManager.LayoutParams getDefaultSystemWindowParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        params.format = PixelFormat.RGBA_8888;
        params.gravity = Gravity.TOP | Gravity.LEFT;
        return params;
    }

}