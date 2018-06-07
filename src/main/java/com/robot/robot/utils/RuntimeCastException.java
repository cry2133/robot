package com.robot.robot.utils;

/**
 * Created by Administrator on 2017/8/21.
 */
public class RuntimeCastException extends RuntimeException {

    private Throwable cause;

    public RuntimeCastException(Throwable throwable) {
        this.cause = throwable;
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }

}