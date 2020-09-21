package com.github.zhangkaitao;

import org.springframework.beans.factory.SmartInitializingSingleton;

/**
 * User: zhangkaitao
 * Date: 14-7-31
 * Time: ����8:11
 * Version: 1.0
 */
public class MySmartInitializingSingleton implements SmartInitializingSingleton {
    //���з�lazy����Beanʵ������ɺ󣬻���ø÷���
    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("����Beanʵ���������");
    }
}
