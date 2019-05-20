package com.integrationtech.recycleviewdragdemo.drag;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.integrationtech.recycleviewdragdemo.R;

import java.util.List;

/**
 * Created by Wongerfeng on 2019/3/22.
 */

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.DragViewHolder> implements ItemTouchHelperListener{

    private List<Integer> mIntegerList;
    private LayoutInflater mInflater;

    public DragAdapter(Context context, List<Integer> integerList) {
        mInflater = LayoutInflater.from(context);
        mIntegerList = integerList;
    }

    @Override
    public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DragViewHolder(mInflater.inflate(R.layout.drag_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(DragViewHolder holder, int position) {
        holder.mTextView.setText(mIntegerList.get(position)+"");
    }

    @Override
    public int getItemCount() {
        return mIntegerList.size();
    }


    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //获取拖拽 的元素
        Integer integer = mIntegerList.get(fromPosition);
        //移除当前元素
        mIntegerList.remove(integer);
        //添加元素到目标位置
        mIntegerList.add(toPosition, integer);
        //更新位置UI
        notifyItemMoved(fromPosition, toPosition);
    }

    public class DragViewHolder extends RecyclerView.ViewHolder implements DragListener{

        private TextView mTextView;

        public DragViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.textView);
        }

        @Override
        public void onItemSelected() {
            mTextView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void omItemFinished() {
            mTextView.setBackgroundColor(Color.WHITE);
        }
    }
}
