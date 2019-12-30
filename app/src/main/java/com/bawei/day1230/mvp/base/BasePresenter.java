package com.bawei.day1230.mvp.base;

import java.lang.ref.WeakReference;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230.mvp.base
 * ClassName:   BasePresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:34
 */
public abstract class BasePresenter<M extends IBaseModel,V extends IBaseView>{
    protected M model;
    private WeakReference<V> weakReference;

    public BasePresenter() {
        model = initModel();
    }

    public void attach(V v){
        weakReference = new WeakReference<>(v);
    }

    protected abstract M initModel();

    public void detach(){
        if (weakReference != null) {
            weakReference.clear();
            weakReference=null;
        }
    }

    public V getView(){
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
