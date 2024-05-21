package com.coala.rest.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.coala.exception.PedidoNaoEncontradoExcepetion;
import com.coala.exception.RegraNegocioException;
import com.coala.rest.ApiErros;

@RestControllerAdvice
public class ApplicationControllerAdivice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(
        RegraNegocioException ex) {
            String mensagemErro = ex.getMessage();
            return new ApiErros(mensagemErro);
        }

    @ExceptionHandler(PedidoNaoEncontradoExcepetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros handlePedidoNotFoundExcepetion(PedidoNaoEncontradoExcepetion p) {
        return new ApiErros(p.getMessage());
    }

}
