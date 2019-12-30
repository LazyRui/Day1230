package com.bawei.day1230.presenter;

import com.bawei.day1230.model.ProductModel;
import com.bawei.day1230.mvp.base.BasePresenter;
import com.bawei.day1230.contract.ProductContract;

import java.util.Map;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230
 * ClassName:   ProductPresenter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:59
 */
public class ProductPresenter extends BasePresenter<ProductModel, ProductContract.IView> implements ProductContract.IPresenter {
    @Override
    public void setProductData(String url, Map<String, String> params) {
        model.getProductData(url, params, new ProductContract.IModel.ProductDataCallBack() {
            @Override
            public void success(Object o) {
                getView().success(o);
            }

            @Override
            public void failure(Throwable throwable) {
                getView().failure(throwable);
            }
        });
    }

    @Override
    protected ProductModel initModel() {
        return new ProductModel();
    }
}
