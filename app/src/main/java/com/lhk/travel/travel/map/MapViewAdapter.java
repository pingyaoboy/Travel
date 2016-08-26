package com.lhk.travel.travel.map;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/16.
 */
public class MapViewAdapter extends RecyclerView.Adapter<MapViewAdapter.ViewHolder> {

    private Activity mActivity;
    private ArrayList<String> images;
    private ImageLoader mImageLoader;
    private Point relativePoint;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(new ImageView(mActivity));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mImageLoader == null)
            mImageLoader = ImageLoader.getmInstance();
        holder.imageView.setImageBitmap(mImageLoader.getImageByName(images.get(position)));
    }

    @Override
    public int getItemCount() {
        if (images == null)
            return 0;
        return images.size();
    }
    //自定义的ViewHolder,减少findViewById调用次数
    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        //在布局中找到所含有的UI组件
        public ViewHolder(View itemView) {
            super(itemView);
//            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            if (itemView instanceof ImageView)
                imageView = (ImageView) itemView;
        }
    }

    private class Point{
        long x, y;
    }
}
