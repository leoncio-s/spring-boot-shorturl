package com.leoncio.shorturl.util;

import java.net.MalformedURLException;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/*
 * Este componente é responsável por valida a url informada pelo usuário e retornará a url formatada
 */

@Component
public class ValidaUrl {

    public String validaUrl(String url) throws MalformedURLException, Exception {

        Pattern p  = Pattern.compile("^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)+|");
        boolean vl = p.matcher(url).find();
        if (vl) {
            return url;
        } else {
            url = "https://" + url;
            if (p.matcher(url).find()) {
                return url;
            } else {
                throw new MalformedURLException();
            }
        }
    }
}
