package com.lhk.travel.travel.fragment.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lhk.travel.travel.R;
import com.lhk.travel.travel.bean.MusicInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by pyboy on 15/10/31.
 */
public class MusicListAdapter extends BaseAdapter {

    private Context mContext = null;
    ArrayList<MusicInfo> mData = null;
    private UpdateHandler mHandler = null;

    protected class UpdateHandler extends Handler{

        WeakReference<MusicListAdapter> mAdapter = null;

        public UpdateHandler(MusicListAdapter adapter){
            mAdapter = new WeakReference<MusicListAdapter>(adapter);
        }
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mAdapter != null && mAdapter.get() != null){
                MusicListAdapter adapter = mAdapter.get();
                adapter.notifyDataSetChanged();
            }
        }
    }

    public MusicListAdapter(Context context, ArrayList<MusicInfo> data){
        mData = data;
        mContext = context;
        mHandler = new UpdateHandler(this);
    }

    //子线程，不能更新
    public void update(ArrayList<MusicInfo> data){
        mData = data;
        mHandler.sendEmptyMessage(0);
    }

    @Override
    public int getCount() {
        if(mData==null)
            return 0;
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.music_list_item_test,parent,false);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.value = (TextView) convertView.findViewById(R.id.value);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mData.get(position).getTitle());
//        holder.value.setText(GetPinyin.toPinYin(mData.get(position).getTitle()));
        holder.value.setText(mData.get(position).getUrl());
        return convertView;
    }

    private class ViewHolder{
        TextView name,value;
    }
}
