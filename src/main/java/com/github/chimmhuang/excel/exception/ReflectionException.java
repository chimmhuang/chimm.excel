package com.github.chimmhuang.excel.exception;

/**
 * @author Chimm Huang
 */
public class ReflectionException extends RuntimeException {

    public ReflectionException() {
    }

    public ReflectionException(String message) {
        super(message);
    }

    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReflectionException(Throwable cause) {
        super(cause);
    }
}
