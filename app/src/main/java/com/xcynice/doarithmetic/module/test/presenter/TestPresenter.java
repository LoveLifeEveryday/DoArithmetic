package com.xcynice.doarithmetic.module.test.presenter;


import com.xcynice.doarithmetic.base.BaseBean;
import com.xcynice.doarithmetic.base.BaseObserver;
import com.xcynice.doarithmetic.base.BasePresenter;
import com.xcynice.doarithmetic.bean.Arithmetic;
import com.xcynice.doarithmetic.module.test.view.ITestView;

import java.util.List;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/13 01:34
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class TestPresenter extends BasePresenter<ITestView> {
    public TestPresenter(ITestView baseView) {
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
