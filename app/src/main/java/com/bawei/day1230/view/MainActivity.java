package com.bawei.day1230.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day1230.R;
import com.bawei.day1230.api.Api;
import com.bawei.day1230.contract.ProductContract;
import com.bawei.day1230.model.entity.ProductEntity;
import com.bawei.day1230.mvp.BaseActivity;
import com.bawei.day1230.presenter.ProductPresenter;
import com.bawei.day1230.view.adapter.MyRecyclerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<ProductPresenter> implements ProductContract.IView {

    @BindView(R.id.rv)
    RecyclerView rv;

    @Override
    protected void initData() {
        Map<String, String> params = new HashMap<>();
        params.put("keyword", "手机");
        params.put("count", "20");
        params.put("page", "1");
        prenseter.setProductData(Api.PRODUCT_URL, params);
    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        rv.setLayoutManager(new GridLayoutManager(this,2));
    }

    @Override
    protected ProductPresenter initPresenter() {
        return new ProductPresenter();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void success(Object o) {
        if (o != null) {
            if (o instanceof ProductEntity) {
                List<ProductEntity.ResultBean> result = ((ProductEntity) o).getResult();
                Log.e("xxx", result.size() + "");


                MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(this,result);
                rv.setAdapter(myRecyclerAdapter);


                myRecyclerAdapter.setAddOnItemClickListener(new MyRecyclerAdapter.AddOnItemClickListener() {
                    @Override
                    public void clickListener(ProductEntity.ResultBean list, int position) {
                        startActivity(new Intent(MainActivity.this,Main2Activity.class));
                    }
                });
            }
        }
    }

    @Override
    public void failure(Throwable throwable) {

    }

}
