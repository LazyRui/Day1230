package com.bawei.day1230.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.day1230.mvp.base.BasePresenter;
import com.bawei.day1230.mvp.base.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230.mvp
 * ClassName:   BaseActivity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:37
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {
    protected P prenseter;
    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        bind = ButterKnife.bind(this);
        prenseter = initPresenter();
        if (prenseter != null) {
            prenseter.attach(this);
        }
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P initPresenter();

    protected abstract int layoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (prenseter != null) {
            prenseter.detach();
        }
        if (bind != null) {
            bind.unbind();
        }
    }
}
