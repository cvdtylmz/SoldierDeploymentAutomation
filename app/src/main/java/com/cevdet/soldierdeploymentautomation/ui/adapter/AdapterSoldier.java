package com.cevdet.soldierdeploymentautomation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.model.Soldier;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterSoldier extends RecyclerView.Adapter<AdapterSoldier.AdapterSoldierViewHolder> {

    private List<Soldier> data;

    public AdapterSoldier(List<Soldier> dataList) {
        this.data = dataList;
    }


    @NonNull
    @Override
    public AdapterSoldierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterSoldierViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSoldierViewHolder holder, int position) {
        Soldier soldier = data.get(position);
        holder.setData(soldier);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AdapterSoldierViewHolder extends RecyclerView.ViewHolder {
        TextView txtCity;

        AdapterSoldierViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews();
        }

        private void bindViews() {
            txtCity = itemView.findViewById(R.id.txt_item_city);
        }

        private void setData(Soldier soldier) {
            txtCity.setText(soldier.getName());
        }
    }
}
