package com.integrationtech.recycleviewdragdemo;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

/**
 * Created by Wongerfeng on 2019/3/20.
 */

public class DragItemTouchHelper extends ItemTouchHelper.Callback {
    private static final String TAG = "MyItemTouchCallBack";

    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始长宽
    private int fixedWidth = 150;

    public DragItemTouchHelper() {
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Log.i(TAG, "getMovementFlags: ");
        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN |  ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlag, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        Log.i(TAG, "onMove: id=" + viewHolder.getItemId() + " oldPosition = "+viewHolder.getOldPosition());
        if (recyclerView.getAdapter() instanceof OnItemTouchHelperListener){
            ((OnItemTouchHelperListener) recyclerView.getAdapter()).onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        Log.i(TAG, "onSwiped: id=" + viewHolder.getItemId() + " oldPosition = "+viewHolder.getOldPosition());
//        if (mAdapter instanceof ViewHolderAdapter){
//            ((ViewHolderAdapter) mAdapter).onItemDismissed(viewHolder.getAdapterPosition());
//        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        Log.i(TAG, "clearView: id=" + viewHolder.getItemId() + " oldPosition = "+viewHolder.getOldPosition());

        if (viewHolder instanceof OnDragVHListener){
            ((OnDragVHListener) viewHolder).onFinished();
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        Log.i(TAG, "onChildDraw: ");
        //仅对侧滑状态下的效果做出改变
    }
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        Log.i(TAG, "onSelectedChanged: ");
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            if (viewHolder instanceof OnDragVHListener){
                ((OnDragVHListener) viewHolder).onSelected();
            }
        }

    }

    @Override
    public boolean isLongPressDragEnabled() {
        Log.i(TAG, "isLongPressDragEnabled: ");
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        Log.i(TAG, "isItemViewSwipeEnabled: ");
        return true;
    }
}