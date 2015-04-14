package com.AntGame.Controller;

/**
 * Created by alexdavis on 21/03/15.
 */
public class OutOfMapException extends Exception {
    public OutOfMapException() { super(); }
    public OutOfMapException(String msg) {super(msg); }
}
