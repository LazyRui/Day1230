package com.bawei.day1230.view;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.day1230.R;
import com.bawei.day1230.mvp.BaseActivity;
import com.bawei.day1230.mvp.base.BasePresenter;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.et_qrcode)
    EditText etQrcode;
    @BindView(R.id.but_qrcode)
    Button butQrcode;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    @BindView(R.id.but1)
    Button but1;
    @BindView(R.id.but2)
    Button but2;
    private Bitmap image;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().hide();
        EventBus.getDefault().register(this);

        ivQrcode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (image != null) {
                    CodeUtils.analyzeByImageView(ivQrcode, new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(Main2Activity.this, result, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {

                        }
                    });
                }


                return true;
            }
        });

    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_main2;
    }


    @OnClick(R.id.but_qrcode)
    public void onViewClicked() {
        String s = etQrcode.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "输入不能为空……", Toast.LENGTH_SHORT).show();
            return;
        }
        image = CodeUtils.createImage(s, 400, 400, null);
        ivQrcode.setImageBitmap(image);
    }


    @OnClick({R.id.but1, R.id.but2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.but1:
                EventBus.getDefault().post("你好啊！");
                break;
            case R.id.but2:
                EventBus.getDefault().post(etQrcode);
                break;
        }
    }






    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getEditText(EditText editText){
        Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
    }


}
