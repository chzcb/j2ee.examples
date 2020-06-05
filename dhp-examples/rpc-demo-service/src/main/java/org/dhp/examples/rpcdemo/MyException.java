package org.dhp.examples.rpcdemo;

public class MyException extends RuntimeException {
    public MyException(String code) {
        super(code);
    }
}
