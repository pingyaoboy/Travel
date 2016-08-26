package com.lhk.travel.travel.service.musicPlayer;

/**
 * Created by pyboy on 15/11/15.
 */
public interface IMusicService {

    //开始
    public void start(String songPath);

    //下一个
    public void next(String songPath);

    //上一个
    public void pre(String songPath);

    //暂停
    public void pause();

    //停止
    public void stop();

    //music 时间长度
    public int getLen();
}
