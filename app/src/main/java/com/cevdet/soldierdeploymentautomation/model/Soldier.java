package com.cevdet.soldierdeploymentautomation.model;

public class Soldier {
    private String name;
    private City city;

    public Soldier() {

    }

    public Soldier(String name, City city) {
        this.name = name;
        this.city = city;
    }

    public Soldier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
