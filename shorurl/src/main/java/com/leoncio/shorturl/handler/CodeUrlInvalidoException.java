package com.leoncio.shorturl.handler;

public class CodeUrlInvalidoException extends RuntimeException {
    public CodeUrlInvalidoException(){
        super("ShortUrl inválida");
    }
}
