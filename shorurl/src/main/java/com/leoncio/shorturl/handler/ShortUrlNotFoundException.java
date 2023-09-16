package com.leoncio.shorturl.handler;

public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException(){
        super("Url não encontrada");
    }
}
