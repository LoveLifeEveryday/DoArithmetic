package com.xcynice.doarithmetic.module.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ring.CircleProgress;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.base.BaseActivity;
import com.xcynice.doarithmetic.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class TestResultActivity extends BaseActivity {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.tv_all_num)
    TextView mTvAllNum;
    @BindView(R.id.cp)
    CircleProgress mCp;
    @BindView(R.id.tv_correct)
    TextView mTvCorrect;

    private String allNum;
    private String correctNum;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_result;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            allNum = bundle.getString("allNum");
            correctNum = bundle.getString("correctNum");
        }

        //沉浸式状态栏
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mIvTitleLeft.setImageResource(R.drawable.ic_back);
        mIvTitleRight.setVisibility(View.INVISIBLE);
        mTvTitleCenter.setText("做题小报告");

        mTvAllNum.setText("总题数:               " + allNum + "道");
        mTvCorrect.setText("回答正确题数:  " + correctNum + "道");
        int numAll = Integer.parseInt(allNum);
        int numCorrect = Integer.parseInt(correctNum);
        mCp.setProgress((float) (numCorrect * 1.0 / numAll * 1.0));
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        finish();
    }
}