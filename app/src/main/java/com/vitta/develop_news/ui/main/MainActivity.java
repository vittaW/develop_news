package com.vitta.develop_news.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.vitta.develop_news.R;
import com.vitta.develop_news.base.activity.RootActivity;
import com.vitta.develop_news.contants.Constants;
import com.vitta.develop_news.ui.about.AboutFragment;
import com.vitta.develop_news.ui.gank.GankMainFragment;
import com.vitta.develop_news.ui.gold.GoldMainFragment;
import com.vitta.develop_news.ui.like.LikeFragment;
import com.vitta.develop_news.ui.setting.SettingFragment;
import com.vitta.develop_news.ui.vtex.VtexMainFragment;
import com.vitta.develop_news.ui.wechat.WechatMainFragment;
import com.vitta.develop_news.ui.zhihu.ZhiHuMainFragment;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class MainActivity extends RootActivity {

    private static final String TAG = "MainActivity";

    //drawer
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.left_slide)
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    //main_content
    ZhiHuMainFragment mZhihuFragment;
    GankMainFragment mGankFragment;
    WechatMainFragment mWechatFragment;
    GoldMainFragment mGoldFragment;
    VtexMainFragment mVtexFragment;
    LikeFragment mLikeFragment;
    SettingFragment mSettingFragment;
    AboutFragment mAboutFragment;
    private int hideFragment = Constants.TYPE_ZHIHU;
    private int showFragment = Constants.TYPE_ZHIHU;

        @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        setToolBar(mToolBar,"知乎日报");
        mZhihuFragment = new ZhiHuMainFragment();
        mGankFragment = new GankMainFragment();
        mWechatFragment = new WechatMainFragment();
        mGoldFragment = new GoldMainFragment();
        mVtexFragment = new VtexMainFragment();
        mLikeFragment = new LikeFragment();
        mSettingFragment = new SettingFragment();
        mAboutFragment = new AboutFragment();
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawer.addDrawerListener(mDrawerToggle);
        //加载Fragment 到 FrameLayout 中
        loadMultipleRootFragment(R.id.main_content,0, mZhihuFragment,mGankFragment,mWechatFragment,mGoldFragment,mVtexFragment,mLikeFragment,mSettingFragment,mAboutFragment);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_zhihu:
                        showFragment = Constants.TYPE_ZHIHU;
                        break;
                    case R.id.drawer_wechat:
                        showFragment = Constants.TYPE_WECHAT;
                        break;
                    case R.id.drawer_gank:
                        showFragment = Constants.TYPE_GANK;
                        break;
                    case R.id.drawer_gold:
                        showFragment = Constants.TYPE_GOLD;
                        break;
                    case R.id.drawer_vtex:
                        showFragment = Constants.TYPE_VTEX;
                        break;
                    case R.id.drawer_like:
                        showFragment = Constants.TYPE_LIKE;
                        break;
                    case R.id.drawer_setting:
                        showFragment = Constants.TYPE_SETTING;
                        break;
                    case R.id.drawer_about:
                        showFragment = Constants.TYPE_ABOUT;
                        break;
                }
                item.setChecked(true);
                mToolBar.setTitle(item.getTitle());
                mDrawer.closeDrawers();
                showHideFragment(getTargetFragment(showFragment),getTargetFragment(hideFragment));
                //缓存的原理嘛
                hideFragment = showFragment;
                return true;
            }
        });
    }

    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_ZHIHU:
                return mZhihuFragment;
            case Constants.TYPE_GANK:
                return mGankFragment;
            case Constants.TYPE_WECHAT:
                return mWechatFragment;
            case Constants.TYPE_GOLD:
                return mGoldFragment;
            case Constants.TYPE_VTEX:
                return mVtexFragment;
            case Constants.TYPE_LIKE:
                return mLikeFragment;
            case Constants.TYPE_SETTING:
                return mSettingFragment;
            case Constants.TYPE_ABOUT:
                return mAboutFragment;
        }
        return mZhihuFragment;
    }
}