package com.cevdet.soldierdeploymentautomation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;
import com.cevdet.soldierdeploymentautomation.listeners.RecyclerViewItemClickListener;
import com.cevdet.soldierdeploymentautomation.model.Deployment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDeployment extends RecyclerView.Adapter<AdapterDeployment.AdapterDeploymentViewHolder> {

    private List<Deployment> data;
    private RecyclerViewItemClickListener listener;


    public AdapterDeployment(List<Deployment> dataList, RecyclerViewItemClickListener listener) {
        this.data = dataList;
        this.listener = listener;

    }


    @NonNull
    @Override
    public AdapterDeploymentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDeploymentViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_deployment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDeploymentViewHolder holder, int position) {
        Deployment deployment = data.get(position);
        holder.setData(deployment);
        holder.itemView.setOnClickListener(view -> listener.onClick(RecyclerViewType.DEPLOYMENT,position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AdapterDeploymentViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameCity;

        AdapterDeploymentViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews();
        }

        private void bindViews() {
            txtNameCity = itemView.findViewById(R.id.txt_item_deployment);
        }

        private void setData(Deployment deployment) {
            txtNameCity.setText(String.format("%s-%s", deployment.getSoldier().getName(), deployment.getCity().getName()));
        }

    }
}
