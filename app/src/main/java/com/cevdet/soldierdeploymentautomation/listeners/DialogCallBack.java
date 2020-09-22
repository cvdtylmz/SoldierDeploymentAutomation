package com.cevdet.soldierdeploymentautomation.listeners;

import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;

public interface DialogCallBack {
    void delete(RecyclerViewType type, int position);
}
