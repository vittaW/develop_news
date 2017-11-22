package com.vitta.develop_news.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 封装snakebar ，以便于统一管理
 */

public class SnackbarUtil {

    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
