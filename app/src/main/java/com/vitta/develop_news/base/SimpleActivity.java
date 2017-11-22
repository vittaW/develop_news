package com.vitta.develop_news.base;

import android.view.View;
import android.view.ViewGroup;

import com.vitta.develop_news.R;
import com.vitta.develop_news.widget.ProgressImageView;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/22 18:29
 * 描述：SimpleActivity
 * 封装了RecyclerView 的 各种状态 ，包括空，错误，loading，和正常显示
 */

public abstract class SimpleActivity<P extends BasePresenter> extends BaseActivity<P> {

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;
    private ViewGroup viewMain;
    private ViewGroup mParent;
    private View viewError;
    private View viewLoading;
    private ProgressImageView ivLoading;
    private int mErrorResource = R.layout.view_error;
    private int currentState = STATE_MAIN;
    private boolean isErrorViewAdded = false;

    @Override
    protected void initView() {
        viewMain = ((ViewGroup) findViewById(R.id.view_main));
        if (viewMain == null){
            throw new IllegalStateException("继承自SimpleActivity的子类的布局中必须包含 view 的名字是 'view_main'");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)){
            throw new IllegalStateException("'view_main' view 的父布局必须是ViewGroup");
        }
        mParent = (ViewGroup) viewMain.getParent();
        //向parent 里填充view
        View.inflate(mContext,R.layout.view_progress, mParent);
        viewLoading = mParent.findViewById(R.id.view_loading);
        ivLoading = (ProgressImageView) viewLoading.findViewById(R.id.iv_progress);
        //loading 隐藏，main 显示
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    //-------------下面这些状态方法，写好了状态下应该执行的逻辑，交给子类去调用---------------------------------------------

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, mErrorResource, mParent);
            viewError = mParent.findViewById(R.id.view_error);
            if (viewError == null) {
                throw new IllegalStateException(
                        "A View should be named 'view_error' in ErrorLayoutResource.");
            }
        }
        hideCurrentView();
        currentState = STATE_ERROR;
        viewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        ivLoading.start();
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                ivLoading.stop();
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                if (viewError != null) {
                    viewError.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void setErrorResource(int errorLayoutResource) {
        this.mErrorResource = errorLayoutResource;
    }

}
