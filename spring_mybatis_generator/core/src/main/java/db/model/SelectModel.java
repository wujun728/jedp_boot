package db.model;

import org.apache.commons.lang3.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: jiluohao@yixin.im
 * Date: 2018-08-29 10:48
 * Description:
 */
public class SelectModel<T,K> {
    private T key;
    private K value;

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public SelectModel() {
    }

    public SelectModel(T key, K value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public String toString() {
        return value.toString();
    }
}
