package com.aluizio.sacola.exceptions;

import javax.swing.*;

public class SacolaNaoEncontradaException extends RuntimeException{
    public SacolaNaoEncontradaException(String message){
        super(message);
    }
}
