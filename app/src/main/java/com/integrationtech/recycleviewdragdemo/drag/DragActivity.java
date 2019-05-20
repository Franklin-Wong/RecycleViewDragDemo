package com.integrationtech.recycleviewdragdemo.drag;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.integrationtech.recycleviewdragdemo.R;

import java.util.ArrayList;
import java.util.List;

public class DragActivity extends AppCompatActivity {
    private static final String TAG = "DragActivity";
    private RecyclerView mRecyclerView;
    private List<Integer> mIntegerList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        mRecyclerView = findViewById(R.id.recyclerView);

        initData();
        initEvent();


    }
    private void initData() {
        for (int i = 0; i < 19;i++){
            mIntegerList.add(i);
        }
        DragAdapter dragAdapter = new DragAdapter(this, mIntegerList);
        mRecyclerView.setAdapter(dragAdapter);
    }

    private void initEvent() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        ChannelItemTouchHelper callback = new ChannelItemTouchHelper(){
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

    }



}
