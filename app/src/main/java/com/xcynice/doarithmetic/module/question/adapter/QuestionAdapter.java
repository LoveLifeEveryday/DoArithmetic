package com.xcynice.doarithmetic.module.question.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.bean.Arithmetic;

import org.jetbrains.annotations.NotNull;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/12 21:13
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description TODO
 */

public class QuestionAdapter extends BaseQuickAdapter<Arithmetic, BaseViewHolder> {
    public QuestionAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder helper, Arithmetic arithmetic) {
        helper.setText(R.id.tv_question, arithmetic.getQuestion());
        helper.setText(R.id.tv_answer, arithmetic.getAnswer());
    }
}
