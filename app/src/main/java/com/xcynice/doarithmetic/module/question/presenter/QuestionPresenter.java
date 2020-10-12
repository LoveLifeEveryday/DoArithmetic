package com.xcynice.doarithmetic.module.question.presenter;


import com.xcynice.doarithmetic.base.BaseBean;
import com.xcynice.doarithmetic.base.BaseObserver;
import com.xcynice.doarithmetic.base.BasePresenter;
import com.xcynice.doarithmetic.bean.Arithmetic;
import com.xcynice.doarithmetic.module.question.view.IQuestionView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/12 20:54
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description QuestionPresenter
 */

public class QuestionPresenter extends BasePresenter<IQuestionView> {
    /**
     * 所有题目的数量，后面分页要用到
     */
    private int mSizeQuestion;
    /**
     * 所有题目
     */
    private List<Arithmetic> arithmeticList = new ArrayList<>();

    public QuestionPresenter(IQuestionView baseView) {
        super(baseView);
    }

    /**
     * 第一次得到问题列表
     *
     * @param num   题目数量
     * @param round 数字范围
     */
    public void getQuestionFirst(String num, String round) {
        addDisposable(apiServer.getArithmetic(num, round), new BaseObserver<BaseBean<List<Arithmetic>>>(baseView) {

            @Override
            public void onSuccess(BaseBean<List<Arithmetic>> o) {
                arithmeticList = o.data;
                mSizeQuestion = arithmeticList.size();
                // 返回 0 到 9 的数据，分页加载啦
                List<Arithmetic> listReturn = arithmeticList.subList(0, 20);
                baseView.setQuestionSuccess(listReturn);
            }

            @Override
            public void onError(String msg) {
                baseView.setQuestionFail(msg);
            }
        });

    }


    /**
     * 加载更多问题
     *
     * @param page 页数
     */
    public void getQuestionMore(int page) {
        if (page * 20 + 20 <= mSizeQuestion) {
            List<Arithmetic> listReturn = arithmeticList.subList(page * 20, page * 20 + 20);
            baseView.setQuestionMoreSuccess(listReturn);
        } else {
            List<Arithmetic> listReturn = arithmeticList.subList(page * 20, mSizeQuestion);
            baseView.setQuestionMoreSuccess(listReturn);
        }
    }
}
