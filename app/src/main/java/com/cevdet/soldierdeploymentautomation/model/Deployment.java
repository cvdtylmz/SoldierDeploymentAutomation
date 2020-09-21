package com.cevdet.soldierdeploymentautomation.model;

public class Deployment {
    private Soldier soldier;
    private City city;

    public Deployment(Soldier soldier, City city) {
        this.soldier = soldier;
        this.city = city;
    }

    public Soldier getSoldier() {
        return soldier;
    }

    public void setSoldier(Soldier soldier) {
        this.soldier = soldier;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
