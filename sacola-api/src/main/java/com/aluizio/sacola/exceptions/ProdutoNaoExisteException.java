package com.aluizio.sacola.exceptions;


public class ProdutoNaoExisteException extends RuntimeException{
    public ProdutoNaoExisteException(String message){
        super(message);
    }
}
