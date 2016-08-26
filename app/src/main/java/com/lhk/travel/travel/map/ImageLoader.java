package com.lhk.travel.travel.map;

import android.graphics.Bitmap;

/**
 * Created by Administrator on 2016/5/16.
 */
public class ImageLoader {

    private static ImageLoader mInstance;
    private ImageLoader(){}

    public static ImageLoader getmInstance(){
        if (mInstance == null)
            mInstance = new ImageLoader();
        return mInstance;
    }

    public Bitmap getImageByName(String image){
        return null;
    }
}
