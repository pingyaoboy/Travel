package com.lhk.travel.travel.bean;

/**
 * Created by pyboy on 16/2/6.
 * 获取数据的接口
 */
public interface IData {
    enum STATE{
      OK, ERROR, EMPTY
    };

    public void finish(STATE state, Object data, String info);
}
