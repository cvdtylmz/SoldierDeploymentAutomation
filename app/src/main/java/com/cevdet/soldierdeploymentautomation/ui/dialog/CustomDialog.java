package com.cevdet.soldierdeploymentautomation.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.enums.DialogType;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    private DialogType dialogType;
    private String message;

    public CustomDialog(@NonNull Context context, DialogType dialogType, String message) {
        super(context);
        this.message = message;
        this.dialogType = dialogType;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        if (this.getWindow() != null){
           setDialog();
           switchType();
           btnListener();
        }

    }

    private void setDialog () {
        Objects.requireNonNull(this.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        width = (width / 10) * 7;
        height = (height / 10) * 5;
        this.getWindow().setLayout(width,height);
    }

    private void btnListener () {
        MaterialButton btnDialog = findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(view -> {
            if (this.isShowing()) this.dismiss();
        });
    }

    private void switchType () {
        TextView txtDialog = findViewById(R.id.txt_dialog);
        txtDialog.setText(message);
        ImageView imgDialog = findViewById(R.id.img_dialog);
        switch (dialogType) {
            case SUCCESS:
                imgDialog.setImageResource(R.drawable.ic_success);
                break;
            case ERROR:
                imgDialog.setImageResource(R.drawable.ic_error);
                break;
        }
    }

}
