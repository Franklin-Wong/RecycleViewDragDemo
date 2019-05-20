package com.integrationtech.recycleviewdragdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Created by Wongerfeng on 2019/4/12.
 */
public class BitmapUtils {
    private static final String TAG = "BitmapUtils";


    /**
     * 获取1/4 大小的缩略图
     * @param path
     * @return
     */
    public static Bitmap createThumbnail(String path){

        //给图片分配资源
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        //设置不分配内存，并可以计算图片尺寸
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);


        return bitmap;

    }

    public static int computeSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){

        int height = options.outHeight;
        int width = options.outWidth;
        Log.i(TAG, "computeSampleSize: height="+height+"----width="+width);

        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth){
            int halfHeight = height / 2;
            int halfWidth = width / 2;
            //计算最大的 insamplesize ，
            while (((halfHeight / inSampleSize) > reqHeight) && ((halfWidth / inSampleSize) > reqWidth)){
                inSampleSize *= 2;
            }
        }
        Log.i(TAG, "computeSampleSize: inSampleSize="+inSampleSize);
        return inSampleSize;

    }

    public static Bitmap decodeBitmapFactoryFromResource(Resources resources, int resId, int reqHeight, int reqWidth){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        options.inSampleSize = computeSampleSize(options, reqWidth, reqHeight);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources, resId, options);
    }




}
