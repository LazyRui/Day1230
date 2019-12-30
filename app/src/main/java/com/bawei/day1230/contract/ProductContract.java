package com.bawei.day1230.contract;

import com.bawei.day1230.mvp.base.IBaseModel;
import com.bawei.day1230.mvp.base.IBaseView;

import java.util.Map;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230.contract
 * ClassName:   ProductContract
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_13:40
 */
public interface ProductContract {
    interface IModel extends IBaseModel{
        void getProductData(String url, Map<String,String> params,ProductDataCallBack productDataCallBack);
        interface ProductDataCallBack{
            void success(Object o);
            void failure(Throwable throwable);
        }
    }
    interface IView extends IBaseView{
        void success(Object o);
        void failure(Throwable throwable);
    }
    interface IPresenter{
        void setProductData(String url, Map<String,String> params);

    }
}
