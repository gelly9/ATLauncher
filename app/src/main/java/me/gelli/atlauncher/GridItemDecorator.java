package me.gelli.atlauncher;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Decorator class that divides the space evenly between items
 */
class GridItemDecorator extends RecyclerView.ItemDecoration {

    private final int screenWidth;
    private final int itemWidth;
    private final int topMargin;

    GridItemDecorator(int itemWidth, int topMargin) {
        this.screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        this.itemWidth = itemWidth;
        this.topMargin = topMargin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //calculate the extra space left
        int extraSpace = screenWidth - HomeActivity.COLUMN_COUNT * itemWidth;
        //the 2 stands for the two sides of the item
        final int spaceDividedEqually = extraSpace / HomeActivity.COLUMN_COUNT / 2;
        outRect.left = spaceDividedEqually;
        outRect.right = spaceDividedEqually;
        outRect.top = topMargin;
        outRect.bottom = topMargin;
    }
}