package com.coala.exception;

public class SenhaIvalidException extends RuntimeException {

    public SenhaIvalidException() {
        super("Senha invalida.");
    }

}
