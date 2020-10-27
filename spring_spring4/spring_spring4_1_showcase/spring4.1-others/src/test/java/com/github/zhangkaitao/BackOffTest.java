package com.github.zhangkaitao;

import junit.framework.Assert;
import org.junit.Test;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * �˱�ʵ�֣�������������ʧ�ܺ��ӳٶ�����ԣ�
 * User: zhangkaitao
 * Date: 14-8-1
 * Time: ����12:50
 * Version: 1.0
 */
public class BackOffTest {

    @Test
    public void testFixedBackOff() {
        long interval = 100;
        long maxAttempts = 10;
        BackOff backOff = new FixedBackOff(interval, maxAttempts);
        BackOffExecution execution = backOff.start();

        for(int i = 1; i <= 10; i++) {
            //ÿ������ʱ����100����
            System.out.println(execution.nextBackOff());
        }
        Assert.assertEquals(BackOffExecution.STOP, execution.nextBackOff());
    }


    @Test
    public void testExponentialBackOff() {
        long initialInterval = 100;//��ʼ���
        long maxInterval = 5 * 1000L;//�����
        long maxElapsedTime = 50 * 1000L;//���ʱ����
        double multiplier = 1.5;//�������������´μ�����ϴεĶ��ٱ���
        ExponentialBackOff backOff = new ExponentialBackOff(initialInterval, multiplier);
        backOff.setMaxInterval(maxInterval);
        //currentElapsedTime = interval1 + interval2 + ... + intervalN;
        backOff.setMaxElapsedTime(maxElapsedTime);

        BackOffExecution execution = backOff.start();

        for(int i = 1; i <= 18; i++) {
            System.out.println(execution.nextBackOff());
        }
        Assert.assertEquals(BackOffExecution.STOP, execution.nextBackOff());
    }
}
