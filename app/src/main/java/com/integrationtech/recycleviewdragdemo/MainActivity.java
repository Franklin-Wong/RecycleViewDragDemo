package com.integrationtech.recycleviewdragdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.integrationtech.recycleviewdragdemo.channels.ChannelActivity;
import com.integrationtech.recycleviewdragdemo.drag.DragActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ImageView mImageView;
    /**
     * 1    static修饰的静态内部类，与外部类是相互独立的，互相没有权限引用其中的变量
     * 2    WeakReference替代强引用，外部类被回收后，弱引用返回空实例
     */
    public static class MyHandler extends Handler{
        private final WeakReference<MainActivity> sReference;

        public MyHandler(MainActivity activity) {
            sReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (sReference != null){
                MainActivity activity = sReference.get();
                if (activity != null){

                    activity.getImageSize();
                }
            }
            int what = msg.what;
        }
    }

    private final MyHandler mMyHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tvDrag).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DragActivity.class));
            }
        });
        findViewById(R.id.tvChannel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ChannelActivity.class));
            }
        });
        findViewById(R.id.tvTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class));
            }
        });

        Bitmap bitmap = BitmapUtils.decodeBitmapFactoryFromResource(getResources(), R.mipmap.ic_launcher, 70, 70);

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Log.i(TAG, "onCreate: height="+height+"----width="+width);

        mImageView = findViewById(R.id.ivImage);
        mImageView.setImageBitmap(bitmap);
    }

    /**
     * 获取图片尺寸
     */
    public void getImageSize(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher, options);

        int height = options.outHeight;
        int width = options.outWidth;

        String mimeType = options.outMimeType;


    }

}
