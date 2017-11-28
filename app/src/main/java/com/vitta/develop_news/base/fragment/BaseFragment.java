package com.vitta.develop_news.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.vitta.develop_news.App;
import com.vitta.develop_news.base.presenter.BasePresenter;
import com.vitta.develop_news.base.BaseView;
import com.vitta.develop_news.dagger.component.DaggerFragmentComponent;
import com.vitta.develop_news.dagger.component.FragmentComponent;
import com.vitta.develop_news.dagger.module.FragmentModule;
import com.vitta.develop_news.util.SnackbarUtil;

import javax.inject.Inject;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/23 14:10
 * 描述：BaseFragment
 *
 * mvp fragment 基类
 *
 * 初始化presenter 对象，绑定presenter 生命周期(onViewCreated onDestoryView)
 */

public abstract class BaseFragment<P extends BasePresenter> extends RootFragment implements BaseView {

    @Inject
    BasePresenter mPresenter;

    //只差inject （this） mPresenter 就可以通过dagger2 实例化了
    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //先inject 才能获得presenter 实例
        initInject();
        if (mPresenter != null){
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        SnackbarUtil.show(((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0), msg);
    }

    //-----------------默认啥都没做------------------------------------------------------------------
    @Override
    public void userNightMode(boolean isNight) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }
    //-----------------默认啥都没做------------------------------------------------------------------
    protected abstract void initInject();
    protected abstract int getLayoutId();
    protected abstract void initView();

}
