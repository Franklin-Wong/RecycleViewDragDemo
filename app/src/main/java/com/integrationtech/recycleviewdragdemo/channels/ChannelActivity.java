package com.integrationtech.recycleviewdragdemo.channels;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.integrationtech.recycleviewdragdemo.R;
import com.integrationtech.recycleviewdragdemo.drag.ChannelItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class ChannelActivity extends AppCompatActivity {

    private static final String TAG = "ChannelActivity";
    private RecyclerView mRecyclerView;
    private List<ChannelEntity> items;
    private List<ChannelEntity> otherItems;
    private ChannelAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        mRecyclerView = findViewById(R.id.recyclerView);
        initData();
        initEvent();


    }
    private void initData() {
        items = new ArrayList<>();
        for (int i= 0; i < 19; i++){
            ChannelEntity entity = new ChannelEntity();
            entity.setName("频道"+i);
            items.add(entity);
        }

        otherItems = new ArrayList<>();
        for (int i= 0; i < 30; i++){
            ChannelEntity entity = new ChannelEntity();
            entity.setName("频道"+i);
            otherItems.add(entity);
        }

        mAdapter = new ChannelAdapter(this, items, otherItems);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void initEvent() {

        ///设置布局管理器
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        //设置拖拽事件回调
        ItemTouchHelper.Callback callback = new ChannelItemTouchHelper();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        //设置item的点击事件
        mAdapter.setItemChangeClickListener(new ChannelAdapter.OnItemChangeClickListener() {
            @Override
            public void onItemClick(ChannelEntity entity, int position) {
                Toast.makeText(ChannelActivity.this, entity.getName(), Toast.LENGTH_SHORT).show();

            }
        });

        //设置标题栏的占位比
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int viewType = mAdapter.getItemViewType(position);
                return viewType == ChannelAdapter.MY_CHANNEL_HEADER || viewType == ChannelAdapter.OTHER_CHANNEL_HEADER ? 4 : 1;
            }
        });
        mAdapter.setItemChangeClickListener(new ChannelAdapter.OnItemChangeClickListener() {
            @Override
            public void onItemClick(ChannelEntity entity, int position) {
                Toast.makeText(ChannelActivity.this, entity.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
