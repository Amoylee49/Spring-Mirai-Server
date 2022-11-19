package net.lz1998.pbbot.method;

import org.springframework.stereotype.Component;

@Component
public class FilterChar {

    public String filterChinese(String chin)
    {
        chin = chin.replaceAll("(查询)|[^\\u4e00-\\u9fa5]", "");
        return chin;
    }

    public static String filterNumber(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    public static String filterAlphabet(String alph)
    {

        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }

    public static String filter(String character)
    {
        character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        return character;
    }
}
