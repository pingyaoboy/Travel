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

import com.lhk.travel.travel.R;
import com.lhk.travel.travel.aidl.IMyAidlInterface;
import com.lhk.travel.travel.aidl.MyServiceAidl;

/**
 * Created by pyboy on 15/10/29.
 */
public class Thrid extends Fragment {

    private IMyAidlInterface aidl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        call();
        return inflater.inflate(R.layout.thrid_fragment,container,false);
    }

    //回调service
    private void call(){
        Intent intent = new Intent(getContext(), MyServiceAidl.class);
        getActivity().bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                aidl = IMyAidlInterface.Stub.asInterface(service);
                try {
                    aidl.invokCallBack();
                    System.out.println("lhk=== yes");
                } catch (RemoteException e) {
                    e.printStackTrace();
                    System.out.println("lhk=== no");
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}
