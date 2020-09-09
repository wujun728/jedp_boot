/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2016 Caratacus
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.baomidou.hibernateplus.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机数工具，单例模式
 *
 * @author Caratacus
 *
 */
public class RandomUtils {

    /**
     * 双重校验锁获取一个Random单例
     *
     * @return
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获得一个[0,max)之间的随机整数。
     *
     * @param max
     * @return
     */
    public static int getRandomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * 获得一个[min, max]之间的随机整数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandomInt(int min, int max) {
        return getRandom().nextInt(max - min + 1) + min;
    }

    /**
     * 获得一个[0,max)之间的长整数。
     *
     * @param max
     * @return
     */
    public static long getRandomLong(long max) {
        return getRandom().nextLong(max);
    }

    /**
     * 从数组中随机获取一个元素
     *
     * @param array
     * @return
     */
    public static <E> E getRandomElement(E[] array) {
        return array[getRandomInt(array.length)];
    }

    /**
     * 从list中随机取得一个元素
     *
     * @param list
     * @return
     */
    public static <E> E getRandomElement(List<E> list) {
        return list.get(getRandomInt(list.size()));
    }

    /**
     * 从set中随机取得一个元素
     *
     * @param set
     * @return
     */
    public static <E> E getRandomElement(Set<E> set) {
        int randomInt = getRandomInt(set.size());
        int i = 0;
        for (E e : set) {
            if (i == randomInt) {
                return e;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个key
     *
     * @param map
     * @return
     */
    public static <K, V> K getMapRandomKey(Map<K, V> map) {
        int randomInt = getRandomInt(map.size());
        int i = 0;
        for (K key : map.keySet()) {
            if (i == randomInt) {
                return key;
            }
            i++;
        }
        return null;
    }

    /**
     * 从map中随机取得一个value
     *
     * @param map
     * @return
     */
    public static <K, V> V getMapRandomValue(Map<K, V> map) {
        int randomInt = getRandomInt(map.size());
        int i = 0;
        for (V value : map.values()) {
            if (i == randomInt) {
                return value;
            }
            i++;
        }
        return null;
    }

    /**
     * 生成一个n位的随机数
     *
     * @param n
     * @return
     */
    public static String getRandNumber(int n) {
        String random = StringUtils.EMPTY;
        if (n > 0 && n < 10) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                builder.append('9');
            }
            int num = TypeConvert.toInt(builder.toString());
            while (random.length() < n) {
                random = TypeConvert.toString(ThreadLocalRandom.current().nextInt(num));
            }
        } else {
            random = "0";
        }
        return random;
    }

}