package com.kuehne_nagel.app.exceptions;

public class CantFindDynamicPropertyException extends Exception {
    public CantFindDynamicPropertyException(String property) {
        super("Can't find an implementation of the " + property + " dynamic property");
    }
}
