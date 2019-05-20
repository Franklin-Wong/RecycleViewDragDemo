package com.integrationtech.recycleviewdragdemo.channels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.integrationtech.recycleviewdragdemo.OnItemTouchHelperListener;
import com.integrationtech.recycleviewdragdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wongerfeng on 2019/3/25.
 */

public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements OnItemTouchHelperListener{

    public static final int MY_CHANNEL_HEADER = 0;
    public static final int MY_CHANNEL_TYPE = 1;
    public static final int OTHER_CHANNEL_TYPE = 2;
    public static final int OTHER_CHANNEL_HEADER = 3;

    public static final int COUNT_HEADER = 1;
    public static final int COUNT_HEADER_PRE = 2;


    private boolean isEditable = false;

    private LayoutInflater mInflater;
    private List<ChannelEntity> mItems = new ArrayList<>();
    private List<ChannelEntity> mOtherItems = new ArrayList<>();
    private OnItemChangeClickListener mItemChangeListenter;

    public void setItemChangeClickListener(OnItemChangeClickListener onItemChangeClickListener) {
        mItemChangeListenter = onItemChangeClickListener;
    }

    public ChannelAdapter(Context context, List<ChannelEntity> items, List<ChannelEntity> otherItems) {
        mItems = items;
        mOtherItems = otherItems;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return MY_CHANNEL_HEADER;
        }else if (position > 0 && position < mItems.size() + 1){
            return MY_CHANNEL_TYPE;
        }else if (position == mItems.size() + 1){
            return OTHER_CHANNEL_HEADER;
        }else {
            return OTHER_CHANNEL_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        ///根据viewType 参数，判断下下周不同布局和viewHolder
        if (viewType == MY_CHANNEL_HEADER){
            view = mInflater.inflate(R.layout.my_channel_header_layout, parent, false);
            MyChannelHeaderViewHolder holder = new MyChannelHeaderViewHolder(view);

            return holder;

        }else if (viewType == MY_CHANNEL_TYPE){
            view = mInflater.inflate(R.layout.my_channel_layout, parent, false);
            MyChannelViewHolder holder = new MyChannelViewHolder(view);
            if (isEditable){
                startEditMode((RecyclerView) parent);
            }else {
                stopEditMode((RecyclerView) parent);
            }
            return holder;
        }else if (viewType == OTHER_CHANNEL_TYPE){
            view = mInflater.inflate(R.layout.my_channel_layout, parent, false);

            return new OtherItemViewHolder(view);
        }else {
            view = mInflater.inflate(R.layout.other_channel_header_layout, parent, false);

            return new OtherItemHeaderViewHolder(view);
        }

    }
    /**
     * 允许编辑
     * @param parent
     */
    private void startEditMode(RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i=0; i < childCount; i++){
            View view = parent.getChildAt(i);
            ImageView imageView = view.findViewById(R.id.ivEdit);
            if (imageView != null){
                imageView.setVisibility(View.VISIBLE);
            }
        }

    }

    /**
     * 禁止编辑状态
     * @param parent
     */
    private void stopEditMode(RecyclerView parent) {
        int childCount = parent.getChildCount();
        for (int i=0; i < childCount; i++){
            View view = parent.getChildAt(i);
            ImageView imageView = view.findViewById(R.id.ivEdit);
            if (imageView != null){
                imageView.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            ChannelEntity entity = null;
            if (holder instanceof MyChannelViewHolder){
                entity = mItems.get(position - COUNT_HEADER);
                ((MyChannelViewHolder) holder).mTextView.setText(entity.getName());
                ((MyChannelViewHolder) holder).mTextView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        return false;
                    }
                });

            } else if (holder instanceof OtherItemViewHolder){
                    entity = mOtherItems.get(position - mItems.size() - COUNT_HEADER_PRE);
                    ((OtherItemViewHolder) holder).mTextView.setText(entity.getName());
            }else if (holder instanceof MyChannelHeaderViewHolder){

                ((MyChannelHeaderViewHolder) holder).mTvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isEditable){
                            ((MyChannelHeaderViewHolder) holder).mTvEdit.setText("完成");

                        }else {
                            ((MyChannelHeaderViewHolder) holder).mTvEdit.setText("编辑");
                        }
                        isEditable = !isEditable;
                    }
                });
//                ((MyChannelHeaderViewHolder) holder).mTextView.setTextSize(16);

            }else if (holder instanceof OtherItemHeaderViewHolder){
//                ((OtherItemHeaderViewHolder) holder).mTextView.setTextSize(16);

            }

    }


    @Override
    public int getItemCount() {
        return mItems.size() + mOtherItems.size() + COUNT_HEADER_PRE;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        ChannelEntity channelEntity = mItems.get(fromPosition - COUNT_HEADER);
        mItems.remove(channelEntity);
        mItems.add(toPosition - COUNT_HEADER, channelEntity);

        notifyItemMoved(fromPosition, toPosition);

    }

    public class MyChannelViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;
        ImageView mIvEdit;

        public MyChannelViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
            mIvEdit = itemView.findViewById(R.id.iv_img);
        }
    }
    public class MyChannelHeaderViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView, mTvEdit;

        public MyChannelHeaderViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
            mTvEdit = itemView.findViewById(R.id.tvEdit);
        }
    }
    public class OtherItemViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;

        public OtherItemViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }
    public class OtherItemHeaderViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;

        public OtherItemHeaderViewHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv);
        }
    }

    public interface OnItemChangeClickListener {
        void onItemClick(ChannelEntity entity, int position);

    }
}
