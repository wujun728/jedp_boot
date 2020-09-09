package com.BBS.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by dyl on 2018/7/19.
 */
public class City {

    private String name;
    private String district;
    private String population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
