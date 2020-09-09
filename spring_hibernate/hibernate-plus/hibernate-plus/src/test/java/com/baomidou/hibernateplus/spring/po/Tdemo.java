package com.baomidou.hibernateplus.spring.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.baomidou.hibernateplus.entity.Convert;

/**
 * <p>
 * Tdemo
 * </p>
 *
 * @author Caratacus
 * @date 2016-12-2
 */
@Entity
@Table(name = "demo")
@DynamicInsert()
@DynamicUpdate()
public class Tdemo extends Convert implements Serializable {

    protected Long id;

    private String demo1;
    private String demo2;
    private String demo3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "demo1")
    public String getDemo1() {
        return demo1;
    }

    public void setDemo1(String demo1) {
        this.demo1 = demo1;
    }

    @Column(name = "demo2")
    public String getDemo2() {
        return demo2;
    }

    public void setDemo2(String demo2) {
        this.demo2 = demo2;
    }

    @Column(name = "demo3")
    public String getDemo3() {
        return demo3;
    }

    public void setDemo3(String demo3) {
        this.demo3 = demo3;
    }
}