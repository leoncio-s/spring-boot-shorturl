package com.leoncio.shorturl.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

/*
 * Este componente é utilizado para gerar um código de tamanho fixo, onde este tamanho é definido no momento em que o método é chamado
 */

@Component
public class CodeGenerate {
    public String code;

    public CodeGenerate(){
        code = getCodeUrl(10);
    }

    private String getCodeUrl(int length) {
        Random rand = new Random();
        final List<String> alfabeto = new ArrayList<>(
                Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R","S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r","s", "t", "u", "v", "w", "x", "y", "z"));
        String codeurl = "";

        for (int x = 1; x <= length; x++) {
            int index = rand.nextInt(alfabeto.size());
            codeurl += alfabeto.get(index);
        }

        return codeurl;
    }
}
