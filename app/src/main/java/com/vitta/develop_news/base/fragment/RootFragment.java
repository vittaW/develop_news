package com.vitta.develop_news.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/23 14:02
 * 描述：RootFragment
 * 填充布局
 * 绑定黄油刀(绑定解绑)
 *
 * 无mvp 的fragment 基类
 *
 */

public abstract class RootFragment extends SupportFragment {

    private View mView;
    private Unbinder mUnBinder;
    protected boolean isInited = false;
    protected Context mContext;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutId(),null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnBinder != null){
            mUnBinder.unbind();
        }
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInited = true;
        initView();
    }

    protected abstract int getLayoutId();
    protected abstract void initView();
}
