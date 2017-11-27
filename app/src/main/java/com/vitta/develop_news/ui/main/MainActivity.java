package com.vitta.develop_news.ui.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.vitta.develop_news.R;
import com.vitta.develop_news.base.activity.RootActivity;

import butterknife.BindView;

public class MainActivity extends RootActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;

        @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Log.e(TAG, "initView: " );
        setToolBar(mToolBar,"知乎日报");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawer.addDrawerListener(mDrawerToggle);
    }
}
