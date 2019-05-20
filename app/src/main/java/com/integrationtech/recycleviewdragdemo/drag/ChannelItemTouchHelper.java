package com.integrationtech.recycleviewdragdemo.drag;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by Wongerfeng on 2019/3/22.
 */

public class ChannelItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "SimpleItemTouchHelper";

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlag;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager){
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
        }else {
            dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        }
        int swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if (viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof ItemTouchHelperListener){
            ItemTouchHelperListener listener = (ItemTouchHelperListener) recyclerView.getAdapter();
            listener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }

        return true;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if (viewHolder instanceof DragListener){
                DragListener listener = (DragListener) viewHolder;
                listener.onItemSelected();
            }
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "clearView:  id=" + viewHolder.getItemId() + " oldPosition = "+viewHolder.getOldPosition());
        if (viewHolder instanceof DragListener){
            DragListener listener = (DragListener) viewHolder;
            listener.omItemFinished();
        }
        super.clearView(recyclerView, viewHolder);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped:  id=" + viewHolder.getItemId() + " oldPosition = "+viewHolder.getOldPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }
}
