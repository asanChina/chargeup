package com.pengjiezhang.chargeup.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Pengjie on 8/31/2015. As we know, RecyclerView is efficient then regular GridView and ListView, but RecyclerView
 * doesn't have XML property "AUTO_FIT", when in XML, you have to manually set the number of columns for the RecyclerView, this is
 * very inconvenient. This class is referred from http://blog.sqisland.com/2014/12/recyclerview-autofit-grid.html
 *
 * for using MyRecyclerView in XML code and have grid like layout, you have to specify either of these two:
 * 1) android:columnWidth: when you want grid has the same width as the screen, but might have vertical scroll(VERTICAL)
 * 2) android:columnHeight: when you want grid has the same height as the screen, but might have horizontal scroll(HORIZONTAL)
 *
 * remember the default orientation inherited from LinearLayoutManager is "VERTICAL"
 */
public class MyRecyclerView extends RecyclerView {
    private GridLayoutManager gridLayoutManager = null;
    private int columnWidth;


    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * This function set up several parts of our recycler view, including set up GridLayoutManager which have auto_fit behavior.
     * This function will be called in several constructors. currently this function has a flaw which only try to find
     * columnWidth in XML code, but in effect, user may only specify columnHeight
     *
     * This function should be changed later to make MyRecyclerView more flexible.
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs){
        int[] attrsArray = null;
        if(attrs != null){
            attrsArray = new int[1];
            attrsArray[0] = android.R.attr.columnWidth;
        }
        TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
        columnWidth = array.getDimensionPixelSize(0, -1);
        array.recycle();
        //it's ok to specify only "1 spanCount" here
        gridLayoutManager = new GridLayoutManager(context, 1);
        gridLayoutManager.setReverseLayout(false);
        setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if(columnWidth > 0){
            int spanCount = Math.max(1, getMeasuredWidth()/columnWidth);
            gridLayoutManager.setSpanCount(spanCount);
        }
    }
}
