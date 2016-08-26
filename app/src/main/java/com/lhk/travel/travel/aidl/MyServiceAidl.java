package com.lhk.travel.travel.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyServiceAidl extends Service {

    private IMyAidlInterface.Stub aidl = new IMyAidlInterface.Stub(){

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            System.out.println("lhk==== basicTypes");
        }

        @Override
        public void invokCallBack() throws RemoteException {
            System.out.println("lhk==== invokCallBack");
        }
    };

    public MyServiceAidl() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return aidl;
    }
}
