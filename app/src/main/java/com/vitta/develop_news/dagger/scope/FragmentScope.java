package com.vitta.develop_news.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 作者：王文婷 邮箱：WVitta@126.com
 * 创建时间：2017/11/23 14:15
 * 描述：FragmentScope
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface FragmentScope {
}
