package com.cevdet.soldierdeploymentautomation.listeners;


import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;

public interface RecyclerViewItemClickListener {
    void onClick(RecyclerViewType type, int position);
}
