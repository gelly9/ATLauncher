package me.gelli.atlauncher;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridItemDecorator extends RecyclerView.ItemDecoration {

    private final int spacing;
    private final int columnCount;

    GridItemDecorator(int spacing, int columnCount) {
        this.columnCount = columnCount;
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // if running on tablet and the items do not fill the screen, center the items using the decoration
        final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        final int itemWidth = view.getContext().getResources().getDimensionPixelSize(R.dimen.app_item_size);
        //calculate the extra space left
        int extraSpace = screenWidth - columnCount * itemWidth;
        //the 2 stands for the two sides of the item
        final int spaceDividedEqually = extraSpace / columnCount / 2;
        outRect.left = spaceDividedEqually;
        outRect.right = spaceDividedEqually;
        outRect.top = spacing;
        outRect.bottom = spacing;
    }
}