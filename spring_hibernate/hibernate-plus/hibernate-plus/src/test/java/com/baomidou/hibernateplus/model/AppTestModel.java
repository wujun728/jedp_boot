package com.baomidou.hibernateplus.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.baomidou.hibernateplus.entity.Convert;


/**
 * <p>
 * AppTestModel
 * </p>
 *
 * @author Caratacus
 * @date 2016-11-29
 */
@Table(name = "app_table")
public class AppTestModel extends Convert {

    private Integer id;
    private String str;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "s_str")
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
