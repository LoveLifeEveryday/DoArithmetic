package com.xcynice.doarithmetic.module.test.view;


import com.xcynice.doarithmetic.base.BaseView;
import com.xcynice.doarithmetic.bean.Arithmetic;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/13 01:33
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public interface ITestView extends BaseView {
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
}
