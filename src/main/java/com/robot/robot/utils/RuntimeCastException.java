package com.robot.robot.utils;

/**
 * Created by Administrator on 2017/8/21.
 */
public class RuntimeCastException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private Throwable cause;

    public RuntimeCastException(Throwable throwable) {
        this.cause = throwable;
    }

    @Override
    public synchronized Throwable getCause() {
        return cause;
    }

}