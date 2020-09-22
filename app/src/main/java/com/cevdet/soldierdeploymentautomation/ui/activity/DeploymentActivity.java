package com.cevdet.soldierdeploymentautomation.ui.activity;


import android.view.KeyEvent;

import com.cevdet.soldierdeploymentautomation.R;
import com.cevdet.soldierdeploymentautomation.enums.DialogType;
import com.cevdet.soldierdeploymentautomation.enums.RecyclerViewType;
import com.cevdet.soldierdeploymentautomation.helper.HelperFunction;
import com.cevdet.soldierdeploymentautomation.listeners.DialogCallBack;
import com.cevdet.soldierdeploymentautomation.listeners.RecyclerViewItemClickListener;
import com.cevdet.soldierdeploymentautomation.model.City;
import com.cevdet.soldierdeploymentautomation.model.Deployment;
import com.cevdet.soldierdeploymentautomation.model.Soldier;
import com.cevdet.soldierdeploymentautomation.ui.BaseActivity;
import com.cevdet.soldierdeploymentautomation.ui.adapter.AdapterCity;
import com.cevdet.soldierdeploymentautomation.ui.adapter.AdapterDeployment;
import com.cevdet.soldierdeploymentautomation.ui.adapter.AdapterSoldier;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class DeploymentActivity extends BaseActivity implements RecyclerViewItemClickListener, DialogCallBack {

    MaterialToolbar toolbar;

    RecyclerView recyclerViewSoldier, recyclerViewCity, recyclerViewDeployment;

    MaterialButton btnAddSoldier, btnAddCity, btnDeployment;


    TextInputEditText edtName, edtCity;


    AdapterSoldier adapterSoldier;
    AdapterCity adapterCity;
    AdapterDeployment adapterDeployment;

    Soldier soldier;
    City city;
    Deployment deployment;

    List<Soldier> soldiers = new ArrayList<>();
    List<City> cities = new ArrayList<>();
    List<Deployment> deploymentList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.act_deployment;
    }

    @Override
    protected void initViews() {
        toolbar = findViewById(R.id.toolbar_deployment);
        setSupportActionBar(toolbar);


        btnAddSoldier = findViewById(R.id.btn_add_soldier);
        btnAddCity = findViewById(R.id.btn_add_city);
        btnDeployment = findViewById(R.id.btn_deploy_soldier);


        edtName = findViewById(R.id.edt_enter_soldier_name);
        edtCity = findViewById(R.id.edt_city_name);


        recyclerViewSoldier = findViewById(R.id.rv_soldier);
        recyclerViewCity = findViewById(R.id.rv_city);
        recyclerViewDeployment = findViewById(R.id.rv_deployment);


        adapterSoldier = new AdapterSoldier(soldiers, this);
        adapterCity = new AdapterCity(cities, this);
        adapterDeployment = new AdapterDeployment(deploymentList, this);

        // prevent recyclerview skipping layout.


        recyclerViewSoldier.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewSoldier.setAdapter(adapterSoldier);

        recyclerViewCity.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewCity.setAdapter(adapterCity);

        recyclerViewDeployment.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerViewDeployment.setAdapter(adapterDeployment);

        // prevent recyclerview skipping layout.


        listeners();
    }

    private void listeners() {
        btnAddSoldier.setOnClickListener(view -> addSoldier(Objects.requireNonNull(edtName.getText()).toString()));
        btnAddCity.setOnClickListener(view -> addCity(Objects.requireNonNull(edtCity.getText()).toString()));
        btnDeployment.setOnClickListener(view -> {
            if (soldiers.size() > 0 && cities.size() > 0) deploySoldiers();
            else
                showDialog(RecyclerViewType.DEPLOYMENT, this, DialogType.ERROR, -1, getResources().getString(R.string.tr_empty_list_deployment_error_message));
        });
        edtKeyListeners();

    }


    private void addSoldier(String name) {
        if (HelperFunction.isStringEmpty(name))
            showDialog(RecyclerViewType.SOLDIER, this, DialogType.ERROR, -1, getResources().getString(R.string.tr_blank_area_error_message));
        else {
            soldier = new Soldier(name);
            soldiers.add(soldier);
            adapterSoldier = new AdapterSoldier(soldiers, this);
            recyclerViewSoldier.setAdapter(adapterSoldier);
            showDialog(RecyclerViewType.SOLDIER, this, DialogType.SUCCESS, -1, getResources().getString(R.string.tr_adding_soldier_success_message));
        }

    }

    private void addCity(String cityName) {
        if (HelperFunction.isStringEmpty(cityName))
            showDialog(RecyclerViewType.CITY, this, DialogType.ERROR, -1, getResources().getString(R.string.tr_blank_area_error_message));

        else {
            city = new City(cityName);
            cities.add(city);
            adapterCity = new AdapterCity(cities, this);
            recyclerViewCity.setAdapter(adapterCity);
            showDialog(RecyclerViewType.CITY, this, DialogType.SUCCESS, -1, getResources().getString(R.string.tr_adding_city_success_message));
        }
    }

    private void deploySoldiers() {
        if (soldiers.size() % cities.size() == soldiers.size()) {
            // then cities number > soldiers
            deploySoldiersRandom();
        }
        if (soldiers.size() % cities.size() == 0) {
            // then we can deploy them equal (cities could be multiple soldier) recursive
            deploySoldierByEquivalent();
        }
        if (soldiers.size() % cities.size() > 0) {
            // then soldiers not equal check list of deployment size's
            deploySoldiersLogical();
        }
    }

    private void deploySoldiersRandom() {
        deploymentList = new ArrayList<>();
        while (0 >= soldiers.size()) {
            int randomSoldierIndex = new Random().nextInt(soldiers.size() - 1);
            int randomCityIndex = new Random().nextInt(cities.size() - 1);
            deployment = new Deployment(new Soldier(soldiers.get(randomSoldierIndex).getName()), new City(cities.get(randomCityIndex).getName()));
            deploymentList.add(deployment);
            soldiers.remove(randomSoldierIndex);
            cities.remove(randomCityIndex);
            notifyAdapter();
        }
    }

    private void deploySoldierByEquivalent() {
        deploymentList = new ArrayList<>();
        int loopSize = soldiers.size() / cities.size();
        int period = cities.size();
        int i = 0;
        List<City> tempCityList = new ArrayList<>();

        while (i <= period) {
            while (0 >= loopSize) {
                if (cities.size() == 0) {
                    int randomSoldierIndex = new Random().nextInt(soldiers.size() - 1);
                    int randomCityIndex = new Random().nextInt(tempCityList.size() - 1);
                    cities.add(tempCityList.get(randomCityIndex));
                    deployment = new Deployment(new Soldier(soldiers.get(randomSoldierIndex).getName()), new City(tempCityList.get(randomCityIndex).getName()));
                    deploymentList.add(deployment);
                    cities.remove(randomSoldierIndex);
                    tempCityList.remove(randomCityIndex);
                    notifyAdapter();

                } else {
                    int randomSoldierIndex = new Random().nextInt(soldiers.size() - 1);
                    int randomCityIndex = new Random().nextInt(cities.size() - 1);
                    tempCityList.add(cities.get(randomCityIndex));
                    deployment = new Deployment(new Soldier(soldiers.get(randomSoldierIndex).getName()), new City(cities.get(randomCityIndex).getName()));
                    deploymentList.add(deployment);
                    soldiers.remove(randomSoldierIndex);
                    cities.remove(randomCityIndex);
                    notifyAdapter();
                }
                loopSize--;
            }
            i++;
        }
    }

    private void deploySoldiersLogical() {
        //  deploymentList = new ArrayList<>();
        //  while (0 >= soldiers.size()) {
        //  }
        // todo: tomorrow...
    }

    private void notifyAdapter() {
        adapterCity.notifyDataSetChanged();
        adapterSoldier.notifyDataSetChanged();
        adapterDeployment.notifyDataSetChanged();
    }


    private void edtKeyListeners() {
        edtName.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                switch (i) {
                    case KeyEvent.KEYCODE_ENTER:
                        addSoldier(Objects.requireNonNull(edtName.getText()).toString());
                        break;
                    case KeyEvent.FLAG_CANCELED_LONG_PRESS:
                        break;
                }
            }
            return false;
        });

        edtCity.setOnKeyListener((view, i, keyEvent) -> {
            if (keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                switch (i) {
                    case KeyEvent.KEYCODE_ENTER:
                        addCity(Objects.requireNonNull(edtCity.getText()).toString());
                        break;
                    case KeyEvent.FLAG_CANCELED_LONG_PRESS:
                        break;
                }
            }
            return false;
        });
    }

    private void deleteSoldier(int position) {
        soldiers.remove(position);
        adapterSoldier.notifyDataSetChanged();

    }

    private void deleteCity(int position) {
        cities.remove(position);
        adapterCity.notifyDataSetChanged();
    }

    private void deleteDeployment(int position) {
        deploymentList.remove(position);
        adapterDeployment.notifyDataSetChanged();
    }

    @Override
    public void onClick(RecyclerViewType type, int position) {
        switch (type) {
            case SOLDIER:
                showDialog(type, this, DialogType.QUESTION, position, getResources().getString(R.string.tr_are_you_to_delete_soldier_message));
                break;
            case CITY:
                showDialog(type, this, DialogType.QUESTION, position, getResources().getString(R.string.tr_are_you_to_delete_city_message));
                break;
            case DEPLOYMENT:
                showDialog(type, this, DialogType.QUESTION, position, getResources().getString(R.string.tr_are_you_to_delete_deployment_message));
                break;
        }
    }

    @Override
    public void delete(RecyclerViewType type, int position) {
        switch (type) {
            case SOLDIER:
                deleteSoldier(position);
                break;
            case CITY:
                deleteCity(position);
                break;
            case DEPLOYMENT:
                deleteDeployment(position);
                break;
        }
    }
}
