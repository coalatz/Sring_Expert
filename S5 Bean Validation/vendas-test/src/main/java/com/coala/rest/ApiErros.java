package com.coala.rest;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    private List<String> erros;

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }

    public ApiErros(String MensagemErro) {
        this.erros = Arrays.asList(MensagemErro);
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "ApiErros [erros=" + erros + "]";
    }

    
}
