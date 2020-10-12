package com.xcynice.doarithmetic.module.question.view;


import com.xcynice.doarithmetic.base.BaseView;
import com.xcynice.doarithmetic.bean.Arithmetic;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/12 20:51
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description IQuestionView
 */

public interface IQuestionView extends BaseView {

    /**
     * 第一次设置问题列表成功
     *
     * @param list 问题列表
     */
    void setQuestionSuccess(List<Arithmetic> list);

    /**
     * 第一次设置问题列表失败
     *
     * @param msg 失败信息
     */
    void setQuestionFail(String msg);

    /**
     * 加载更多问题列表成功
     *
     * @param list 问题列表
     */
    void setQuestionMoreSuccess(List<Arithmetic> list);

    /**
     * 加载更多问题列表失败
     *
     * @param msg 失败信息
     */
    void setQuestionMoreFail(String msg);
}
