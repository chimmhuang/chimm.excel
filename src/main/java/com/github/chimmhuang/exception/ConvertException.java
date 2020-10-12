package com.github.chimmhuang.exception;

/**
 * @author Chimm Huang
 */
public class ConvertException extends RuntimeException {

    public ConvertException() {
    }

    public ConvertException(String message) {
        super(message);
    }

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(Throwable cause) {
        super(cause);
    }
}
