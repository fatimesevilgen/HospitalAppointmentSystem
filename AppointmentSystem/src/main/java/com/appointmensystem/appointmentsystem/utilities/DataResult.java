package com.appointmensystem.appointmentsystem.utilities;

public class DataResult<T> extends Result{
    public T Data;

    public DataResult(T data, boolean success){
        super(success);
        this.Data = data;
    }

    public DataResult(T data, boolean success, String message){
        super(success, message);
        this.Data = data;
    }


}
