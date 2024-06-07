package com.mftplus.ecommerce.api.dto;

public class DataChange<T> {

    private ChangeType changeType;

    private T data;

    public ChangeType getChangeType() {
        return changeType;
    }

    public DataChange(){

    }

    public DataChange(ChangeType changeType, T data){
        this.changeType = changeType;
        this.data = data;
    }

    public DataChange<T> setChangeType(ChangeType changeType) {
        this.changeType = changeType;
        return this;
    }

    public T getData() {
        return data;
    }

    public DataChange<T> setData(T data) {
        this.data = data;
        return this;
    }

    public enum ChangeType {
        INSERT,
        UPDATE,
        DELETE
    }
}
