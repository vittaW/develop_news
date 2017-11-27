package com.vitta.develop_news.base.presenter;

import com.vitta.develop_news.base.BaseView;
import com.vitta.develop_news.component.RxBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/22 19:03
 * 描述：RxPresenter
 *
 * Created by yanbo on 2017/5/26.
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * unsubscribe() 这个方法很重要，
 * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，
 * 这个引用如果不能及时被释放，将有内存泄露的风险。
 *
 *
 * 看似很完美, 但我们忽略了一点, 如果在请求的过程中Activity已经退出了, 这个时候如果回到主线程去更新UI, 那么APP肯定就崩溃了,.
 * 怎么办呢, 上一节我们说到了Disposable , 说它是个开关, 调用它的dispose()方法时就会切断水管, 使得下游收不到事件, 既然收不到事件, 那么也就不会再去更新UI了.
 * 因此我们可以在Activity中将这个Disposable 保存起来, 当Activity退出时,(detachView) 切断它即可.
 那如果有多个Disposable 该怎么办呢, RxJava中已经内置了一个容器CompositeDisposable,
 每当我们得到一个Disposable时就调用CompositeDisposable.add()将它添加到容器中, 在退出的时候, 调用CompositeDisposable.clear() 即可切断所有的水管.
 *
 *
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {

    protected T mView;
    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    protected <U> void addRxBusSubscribe(Class<U> eventType, Consumer<U> act) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(RxBus.getDefault().toDefaultFlowable(eventType, act));
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }
}

