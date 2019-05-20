package com.integrationtech.recycleviewdragdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "RecyclerViewActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mViewHolderAdapter;
    private ItemTouchHelper mItemTouchHelper;
    private List<Integer> mIntegerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mRecyclerView = findViewById(R.id.rvList);
        initData();
        initEvent();
    }

    private void initData() {
        mIntegerList = new ArrayList<>();
        for (int i=0;i<19;i++){
            mIntegerList.add(i);
        }
        mViewHolderAdapter = new ViewHolderAdapter(this, mIntegerList);
        mRecyclerView.setAdapter(mViewHolderAdapter);
    }

    private void initEvent() {

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mItemTouchHelper = new ItemTouchHelper(new DragItemTouchHelper());
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

    }

}
