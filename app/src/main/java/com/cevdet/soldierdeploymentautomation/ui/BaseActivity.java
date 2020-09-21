package com.cevdet.soldierdeploymentautomation.ui;

import android.os.Bundle;

import com.cevdet.soldierdeploymentautomation.enums.DialogType;
import com.cevdet.soldierdeploymentautomation.ui.dialog.CustomDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    CustomDialog dialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initViews();
    }

    protected abstract int getLayoutId();

    protected abstract void initViews();

    protected void showDialog (DialogType dialogType, String message) {
        dialog =  new CustomDialog(this, dialogType,message);
        dialog.setCanceledOnTouchOutside(false);
        if (!dialog.isShowing()) dialog.show();
    }


}
