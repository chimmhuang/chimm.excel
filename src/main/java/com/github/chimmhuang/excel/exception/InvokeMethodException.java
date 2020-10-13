package com.github.chimmhuang.excel.exception;

/**
 * @author Chimm Huang
 */
public class InvokeMethodException extends RuntimeException {

    public InvokeMethodException() {
    }

    public InvokeMethodException(String message) {
        super(message);
    }

    public InvokeMethodException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvokeMethodException(Throwable cause) {
        super(cause);
    }
}
