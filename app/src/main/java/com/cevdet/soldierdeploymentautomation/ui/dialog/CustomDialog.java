package com.cevdet.soldierdeploymentautomation.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.enums.DialogType;
import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;
import com.cevdet.soldierdeploymentautomation.listeners.DialogCallBack;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog {
    private DialogType dialogType;
    private String message;
    private MaterialButton btnDialogNegative, btnDialogPositive;
    private DialogCallBack listener;
    private RecyclerViewType recyclerViewType;
    private int adapterPosition;


    public CustomDialog(@NonNull Context context, RecyclerViewType recyclerViewType, DialogCallBack listener, DialogType dialogType, int adapterPosition, String message) {
        super(context);
        this.message = message;
        this.dialogType = dialogType;
        this.listener = listener;
        this.adapterPosition = adapterPosition;
        this.recyclerViewType = recyclerViewType;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        if (this.getWindow() != null) {
            setDialog();
            switchType();
            btnListener();
        }

    }

    private void setDialog() {
        Objects.requireNonNull(this.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        width = (width / 10) * 7;
        height = (height / 10) * 5;
        this.getWindow().setLayout(width, height);
    }

    private void btnListener() {
        //test

        btnDialogNegative.setOnClickListener(view -> {
            if (this.isShowing()) this.dismiss();
        });
        btnDialogPositive.setOnClickListener(view -> {
            listener.delete(recyclerViewType, adapterPosition);
            if (this.isShowing()) this.dismiss();
        });
    }

    private void switchType() {
        btnDialogNegative = findViewById(R.id.btn_dialog_negative);
        btnDialogPositive = findViewById(R.id.btn_dialog_positive);
        TextView txtDialog = findViewById(R.id.txt_dialog);
        txtDialog.setText(message);
        ImageView imgDialog = findViewById(R.id.img_dialog);
        switch (dialogType) {
            case SUCCESS:
                imgDialog.setImageResource(R.drawable.ic_success);
                btnDialogPositive.setVisibility(View.GONE);
                break;
            case ERROR:
                imgDialog.setImageResource(R.drawable.ic_error);
                btnDialogPositive.setVisibility(View.GONE);
                break;
            case QUESTION:
                imgDialog.setImageResource(R.drawable.ic_question);
                break;
        }
    }

}
