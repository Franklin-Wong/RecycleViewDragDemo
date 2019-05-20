package com.integrationtech.recycleviewdragdemo.drag;

/**
 * Created by Wongerfeng on 2019/3/22.
 */

public interface ItemTouchHelperListener {
    /**
     * 拖拽位移
     * @param fromPosition
     * @param toPosition
     */
    public void onItemMove(int fromPosition, int toPosition);


}
