package com.robot.common.exception;


/**
 * 
 * @author gaofan
 * @date 2016-7-4 下午2:13:58
 */

public class ImportFailException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private int failCount;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public int getFailCount() {
        return failCount;
    }

    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    public ImportFailException(String message) {
        super(message);
        
    }

    public ImportFailException(Throwable cause,int failCount) {
        super(cause);
        this.failCount = failCount;
    }

    public ImportFailException(Throwable cause) {
        super(cause);
        
    }
    
    
}
