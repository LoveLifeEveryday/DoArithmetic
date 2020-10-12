package com.xcynice.doarithmetic.module.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.base.BaseActivity;
import com.xcynice.doarithmetic.bean.Arithmetic;
import com.xcynice.doarithmetic.module.test.presenter.TestPresenter;
import com.xcynice.doarithmetic.module.test.view.ITestView;
import com.xcynice.doarithmetic.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xcynice.doarithmetic.module.question.activity.QuestionActivity.NUM;
import static com.xcynice.doarithmetic.module.question.activity.QuestionActivity.ROUND;

public class TestActivity extends BaseActivity<TestPresenter> implements ITestView {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_question)
    TextView mTvQuestion;
    @BindView(R.id.et_result)
    EditText mEtResult;
    @BindView(R.id.btn_skip)
    Button mBtnAddToCart;
    @BindView(R.id.btn_go)
    Button mBtnBuyNow;
    @BindView(R.id.ll_buy_all)
    LinearLayout mLlBuyAll;

    private List<Arithmetic> mList = new ArrayList<>();
    private String mNum;
    private String mScope;
    private int i = 0;
    private int size;
    private int correctSize = 0;

    @Override
    protected TestPresenter createPresenter() {
        return new TestPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mNum = bundle.getString(NUM);
            mScope = bundle.getString(ROUND);
        }
        //沉浸式状态栏
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mIvTitleLeft.setImageResource(R.drawable.ic_back);
        mIvTitleRight.setVisibility(View.INVISIBLE);
        mTvTitleCenter.setText("做题");

    }

    @Override
    protected void initData() {
        presenter.getQuestionFirst(mNum, mScope);
    }


    @Override
    public void setQuestionSuccess(List<Arithmetic> list) {
        mList = list;
        size = mList.size();
        mTvQuestion.setText(list.get(i).getQuestion());

    }

    @Override
    public void setQuestionFail(String msg) {
        ToastUtil.showToast(msg);
    }


    @OnClick({R.id.iv_title_left, R.id.btn_skip, R.id.btn_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.btn_skip:
                i++;
                if (i < size) {
                    mTvQuestion.setText(mList.get(i).getQuestion());
                } else {
                    ToastUtil.showToast("跳转到下个界面");
                }
                break;
            case R.id.btn_go:
                if (!TextUtils.isEmpty(mEtResult.getText().toString())) {
                    if (mList.get(i).getAnswer().equals(mEtResult.getText().toString())) {
                        correctSize++;
                    }
                    i++;
                    if (i < size) {
                        mTvQuestion.setText(mList.get(i).getQuestion());
                    } else {
                        ToastUtil.showToast("跳转到下个界面");
                    }
                }else {
                    ToastUtil.showToast("你还没作答吖");
                }
                break;
            default:
        }
    }
}