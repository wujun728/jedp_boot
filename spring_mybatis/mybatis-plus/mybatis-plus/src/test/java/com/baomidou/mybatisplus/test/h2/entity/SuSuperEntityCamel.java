package com.baomidou.mybatisplus.test.h2.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;


/**
 * <p>
 * 多层集成测试
 * github #170
 * </p>
 *
 * @author yuxiaobin
 * @since 2017/12/7
 */
public abstract class SuSuperEntityCamel {

    @TableField(value = "lastUpdatedDt", fill = FieldFill.UPDATE)
    private Date lastUpdatedDt;

    public Date getLastUpdatedDt() {
        return lastUpdatedDt;
    }

    public void setLastUpdatedDt(Date lastUpdatedDt) {
        this.lastUpdatedDt = lastUpdatedDt;
    }
}
