package com.bawei.day1230.model;

import com.bawei.day1230.contract.ProductContract;
import com.bawei.day1230.model.entity.ProductEntity;
import com.bawei.day1230.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.util.Map;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230
 * ClassName:   ProductModel
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:55
 */
public class ProductModel implements ProductContract.IModel {
    @Override
    public void getProductData(String url, Map<String, String> params, ProductDataCallBack productDataCallBack) {
        OkHttpUtils.getInstance().doGet(url, params, new OkHttpUtils.OkHttpDataCallBack() {
            @Override
            public void success(String json) {
                if (productDataCallBack != null) {
                    ProductEntity productEntity = new Gson().fromJson(json, ProductEntity.class);
                    productDataCallBack.success(productEntity);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (productDataCallBack != null) {
                    productDataCallBack.failure(throwable);
                }
            }
        });
    }
}
