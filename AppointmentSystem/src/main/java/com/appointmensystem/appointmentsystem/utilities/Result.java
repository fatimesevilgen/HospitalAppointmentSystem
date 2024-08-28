package com.appointmensystem.appointmentsystem.utilities;


public class Result{
    public String Message;
    public boolean Success;

    public Result(Boolean success){
        Success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        Message = message;
    }
}


