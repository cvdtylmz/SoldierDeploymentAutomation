package com.cevdet.soldierdeploymentautomation.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;
import com.cevdet.soldierdeploymentautomation.listeners.RecyclerViewItemClickListener;
import com.cevdet.soldierdeploymentautomation.model.City;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterCity extends RecyclerView.Adapter<AdapterCity.AdapterCityViewHolder> {

    private List<City> data;
    private RecyclerViewItemClickListener listener;

    public AdapterCity(List<City> dataList, RecyclerViewItemClickListener listener) {
        this.listener = listener;
        this.data = dataList;

    }


    @NonNull
    @Override
    public AdapterCityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterCityViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCityViewHolder holder, int position) {
        City city = data.get(position);
        holder.setData(city);
        holder.itemView.setOnClickListener(view -> listener.onClick(RecyclerViewType.CITY,position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AdapterCityViewHolder extends RecyclerView.ViewHolder {
        TextView txtCity;

        AdapterCityViewHolder(@NonNull View itemView) {
            super(itemView);
            bindViews();
        }

        private void bindViews() {
            txtCity = itemView.findViewById(R.id.txt_item_city);
        }

        private void setData(City city) {
            txtCity.setText(city.getName());
        }
    }
}
