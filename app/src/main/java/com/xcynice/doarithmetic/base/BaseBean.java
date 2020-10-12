package com.xcynice.doarithmetic.base;

import java.io.Serializable;


/**
 * @Author 许朋友爱玩
 * @Date 2020/6/12
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description BaseBean
 */

public class BaseBean<T> implements Serializable {


    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    public int code;
    public String message;
    public T data;

    public BaseBean(int code, T data) {
        this.code = code;
        this.data = data;
    }
}
