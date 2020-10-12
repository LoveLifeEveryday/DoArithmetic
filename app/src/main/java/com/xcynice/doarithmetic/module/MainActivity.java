package com.xcynice.doarithmetic.module;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.base.BaseActivity;
import com.xcynice.doarithmetic.base.BasePresenter;
import com.xcynice.doarithmetic.module.question.activity.QuestionActivity;
import com.xcynice.doarithmetic.module.test.activity.TestActivity;
import com.xcynice.doarithmetic.util.ToastUtil;
import com.xcynice.doarithmetic.util.XUtil;

import butterknife.BindView;
import butterknife.OnClick;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import static com.xcynice.doarithmetic.module.question.activity.QuestionActivity.NUM;
import static com.xcynice.doarithmetic.module.question.activity.QuestionActivity.ROUND;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/11
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MainActivity
 */


public class MainActivity extends BaseActivity {

    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.et_range)
    ExtendedEditText mEtRange;
    @BindView(R.id.tfb_range)
    TextFieldBoxes mTfbRange;
    @BindView(R.id.et_count)
    ExtendedEditText mEtCount;
    @BindView(R.id.tfb_count)
    TextFieldBoxes mTfbCount;
    @BindView(R.id.btn_see_questions)
    Button mBtnSeeQuestions;
    @BindView(R.id.btn_practise)
    Button mBtnPractise;


    private boolean mIsScopeError = true;
    private boolean mIsCountError = true;

    @SuppressWarnings("rawtypes")
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mIvTitleLeft.setVisibility(View.INVISIBLE);
        mIvTitleRight.setVisibility(View.INVISIBLE);
        mTvTitleCenter.setText("计算易，让计算更容易");

        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mBtnSeeQuestions.setBackgroundColor(XUtil.getColor(R.color.deep_gray));
        mBtnPractise.setBackgroundColor(XUtil.getColor(R.color.deep_gray));
        mTfbRange.setSimpleTextChangeWatcher((theNewText, isError) -> {
            if (!TextUtils.isEmpty(theNewText) && !isError) {
                if (Integer.parseInt(theNewText) >= 2) {
                    mIsScopeError = false;
                } else {
                    mIsScopeError = true;
                }
                enableBtn(theNewText);
            }
        });

        mTfbCount.setSimpleTextChangeWatcher((theNewText, isError) -> {
            if (!TextUtils.isEmpty(theNewText) && !isError) {

                if (Integer.parseInt(theNewText) >= 1 && Integer.parseInt(theNewText) <= 10000) {
                    mIsCountError = false;
                } else {
                    mIsCountError = true;
                }
                enableBtn(theNewText);
            }
        });


    }

    /**
     * 根据状态判断是否启用按钮
     *
     * @param theNewText 输入框的内容
     */
    private void enableBtn(String theNewText) {
        //如果满足条件且非空
        if (!mIsScopeError && !mIsCountError && !TextUtils.isEmpty(theNewText)) {
            mBtnSeeQuestions.setBackgroundColor(XUtil.getColor(R.color.pink));
            mBtnPractise.setBackgroundColor(XUtil.getColor(R.color.green_light));
        }
    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.btn_see_questions, R.id.btn_practise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_see_questions:
                Intent intent = new Intent(this, QuestionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(NUM, mEtCount.getText().toString());
                bundle.putString(ROUND, mEtRange.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_practise:
                Intent intent2 = new Intent(this, TestActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString(NUM, mEtCount.getText().toString());
                bundle2.putString(ROUND, mEtRange.getText().toString());
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            default:
        }
    }
}