package com.xcynice.doarithmetic.module.question.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.xcynice.doarithmetic.R;
import com.xcynice.doarithmetic.base.BaseActivity;
import com.xcynice.doarithmetic.bean.Arithmetic;
import com.xcynice.doarithmetic.module.question.adapter.QuestionAdapter;
import com.xcynice.doarithmetic.module.question.presenter.QuestionPresenter;
import com.xcynice.doarithmetic.module.question.view.IQuestionView;
import com.xcynice.doarithmetic.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity<QuestionPresenter> implements IQuestionView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    public static final String NUM = "com/xcynice/doarithmetic/module/getQuestion/activity/QuestionActivity.java.num";
    public static final String ROUND = "com/xcynice/doarithmetic/module/getQuestion/activity/QuestionActivity.java.round";
    @BindView(R.id.iv_title_left)
    ImageView mIvTitleLeft;
    @BindView(R.id.tv_title_center)
    TextView mTvTitleCenter;
    @BindView(R.id.iv_title_right)
    ImageView mIvTitleRight;
    @BindView(R.id.rl_title)
    RelativeLayout mRlTitle;
    @BindView(R.id.rv_question)
    RecyclerView mRvQuestion;


    private QuestionAdapter mAdapter;
    private String mNum;
    private String mRound;
    /**
     * 上一次加载的数量，方便进行是否加载到最后一页的判断： if (mCurrentCounter < TOTAL_COUNTER)
     */
    private int mCurrentCounter;

    /**
     * 每一次加载的数量
     */
    private final static int SINGLE_PAGE_TOTAL_COUNTER = 20;

    /**
     * 记录分页，方便进行加载更多
     */
    private int mPage = 0;
    private List<Arithmetic> mList = new ArrayList<>();

    /**
     * 记录点击事件的位置，方便后面进行显示答案
     */
    private int mPosition;

    @Override
    protected QuestionPresenter createPresenter() {
        return new QuestionPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mNum = bundle.getString(NUM);
            mRound = bundle.getString(ROUND);
        }

        //沉浸式状态栏
        ImmersionBar.with(this).titleBar(mRlTitle).init();
        mIvTitleLeft.setImageResource(R.drawable.ic_back);
        mIvTitleRight.setVisibility(View.INVISIBLE);
        mTvTitleCenter.setText("四则运算题");
        mAdapter = new QuestionAdapter(R.layout.item_question);
        mRvQuestion.setLayoutManager(new LinearLayoutManager(this));
        mAdapter.openLoadAnimation();
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnLoadMoreListener(this, mRvQuestion);
        mRvQuestion.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        presenter.getQuestionFirst(mNum, mRound);
    }


    @Override
    public void setQuestionSuccess(List<Arithmetic> list) {
        mList = list;
        mCurrentCounter = list.size();
        mAdapter.setNewData(list);
    }

    @Override
    public void setQuestionFail(String msg) {
        ToastUtil.showToast(msg);
    }

    @Override
    public void setQuestionMoreSuccess(List<Arithmetic> list) {
        mList = list;
        mCurrentCounter = list.size();
        mAdapter.addData(list);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void setQuestionMoreFail(String msg) {
        ToastUtil.showToast(msg);
        mAdapter.loadMoreFail();
    }

    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        mPosition = position;

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMoreRequested() {
        if (mCurrentCounter < SINGLE_PAGE_TOTAL_COUNTER) {
            //数据加载完毕，没有更多的数据
            mAdapter.loadMoreEnd();
        } else {
            presenter.getQuestionMore(++mPage);
        }
    }
}