package com.integrationtech.recycleviewdragdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Wongerfeng on 2019/3/19.
 */

public class ViewHolderAdapter extends android.support.v7.widget.RecyclerView.Adapter<android.support.v7.widget.RecyclerView.ViewHolder>
implements OnItemTouchHelperListener{

    private static final String TAG = "ViewHolderAdapter";
    private List<Integer> mIntegerList;
    private LayoutInflater mInflater;


    public ViewHolderAdapter(Context context, List<Integer> integerList) {
        mIntegerList = integerList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Integer integer = mIntegerList.get(fromPosition);
        mIntegerList.remove(fromPosition);
        mIntegerList.add(toPosition, integer);
        notifyItemMoved(fromPosition, toPosition);

    }



    class  ViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener{
        TextView mTextView;
        TextView itemText;
        ImageView mImageView;
        LinearLayout item;
        public ViewHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.item);
            itemText = itemView.findViewById(R.id.itemText);
            mTextView = itemView.findViewById(R.id.tv_text);
            mImageView = itemView.findViewById(R.id.iv_img);
        }

        @Override
        public void onSelected() {
            item.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onFinished() {
            item.setBackgroundColor(0);
        }
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    /**
     * 创建布局，生成ViewHolder对象
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i(TAG, "onCreateViewHolder: viewType="+viewType);

        if (viewType == 0){

        }else {

        }
        View view = mInflater.inflate(R.layout.rv_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (position == 0){
//            return;
//        }
        Integer integer = mIntegerList.get(position);
        Log.i(TAG, "onBindViewHolder: integer="+integer);

        if (holder instanceof ViewHolder){
            ((ViewHolder) holder).itemText.setText(String.valueOf(integer));
        }
    }
    @Override
    public int getItemCount() {
        return mIntegerList.size();
    }

}
