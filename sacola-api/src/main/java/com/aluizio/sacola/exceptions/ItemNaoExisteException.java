package com.aluizio.sacola.exceptions;

public class ItemNaoExisteException extends RuntimeException{
    public ItemNaoExisteException(String message){
        super(message);
    }
}
