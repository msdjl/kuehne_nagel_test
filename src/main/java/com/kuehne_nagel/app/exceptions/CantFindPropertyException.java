package com.kuehne_nagel.app.exceptions;

public class CantFindPropertyException extends Exception {
    public CantFindPropertyException(String property) {
        super("Can't find the " + property + " property");
    }
}
