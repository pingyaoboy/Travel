// IMusicPlayer.aidl
package com.lhk.travel.travel.aidl;

// Declare any non-default types here with import statements

interface IMusicPlayer {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);
        //开始
        void start(String songPath);

        //下一个
        void next(String songPath);

        //上一个
        void pre(String songPath);

        //暂停
        void pause();

        //停止
        void stop();

        //music 时间长度
        int getLen();
}
