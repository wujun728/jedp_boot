package com.springmvc.entity;

/**
 * Created by zsq on 16/12/20.
 */
public class DemoObject {

    private Long id;

    private String name;

    public DemoObject(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
