package com.integrationtech.recycleviewdragdemo;

/**
 * Created by Wongerfeng on 2019/3/20.
 *@author Administion
 * /

/**
 * 处理拖拽事件
 */
public interface OnItemTouchHelperListener {

    void onItemMove(int fromPosition, int toPosition);

//    void onItemDismissed(int position);

}
