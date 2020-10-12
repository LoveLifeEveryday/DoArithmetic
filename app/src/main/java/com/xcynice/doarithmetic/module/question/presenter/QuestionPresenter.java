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
                baseView.setQuestionSuccess(o.data);
            }

            @Override
            public void onError(String msg) {
                baseView.setQuestionFail(msg);
            }
        });

    }


}
