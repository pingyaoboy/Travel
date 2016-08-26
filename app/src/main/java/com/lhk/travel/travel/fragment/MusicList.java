package com.lhk.travel.travel.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lhk.travel.travel.R;
import com.lhk.travel.travel.Utils.MusicLoader;
import com.lhk.travel.travel.aidl.IMusicPlayer;
import com.lhk.travel.travel.bean.IData;
import com.lhk.travel.travel.bean.MusicInfo;
import com.lhk.travel.travel.fragment.adapter.MusicListAdapter;
import com.lhk.travel.travel.myview.GuideListView;
import com.lhk.travel.travel.service.musicPlayer.MusicService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pyboy on 15/10/31.
 */
public class MusicList extends Fragment implements IData{

    private ListView musicList;
    private MusicListAdapter listAdapter;
    ArrayList<MusicInfo> list = null;
    private IMusicPlayer musicController;
    private class MusicConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicController = (IMusicPlayer) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.music_list,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GuideListView letterListView = (GuideListView) getView().findViewById(R.id.letter_listview);
        musicList = (ListView) getView().findViewById(R.id.music_list);
        listAdapter = new MusicListAdapter(getContext(),list);
        musicList.setAdapter(listAdapter);

        letterListView.setOnTouchingLetterChangedListener(new GuideListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicInfo info = (MusicInfo) listAdapter.getItem(position);
                System.out.println("lhk===" + info.toString());
                if (musicController != null)
                    try {
                        musicController.start(info.getUrl());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
            }
        });
        //刷新数据
        refreshList();
        //开服务
        Intent intent = new Intent(getContext(), MusicService.class);
        getActivity().bindService(intent, new MusicConn(), Context.BIND_AUTO_CREATE);
    }

    private void refreshList(){
        new MusicListRefresh(this).start();
    }

    @Override
    public void finish(STATE state, Object data, String info){
        switch (state){
            case OK:
                list = (ArrayList<MusicInfo>) data;
                listAdapter.update(list);
                break;
            case EMPTY:
                break;
            case ERROR:
                break;
        }
    }

    private class MusicListRefresh extends Thread{

        private IData mData;

        public MusicListRefresh(IData data){
            mData = data;
        }
        @Override
        public void run() {
            super.run();
            MusicLoader loader = MusicLoader.instance(getContext().getContentResolver());
            if (loader != null){
                List<MusicInfo> list = loader.getMusicList();
                if (list != null && list.size() > 0) {
                    mData.finish(IData.STATE.OK, list, "");
                    return;
                }
                mData.finish(IData.STATE.EMPTY, list, "");
            }
            mData.finish(IData.STATE.ERROR, null, "没有获取到 MusicLoader");
            return;
        }
    }

}
