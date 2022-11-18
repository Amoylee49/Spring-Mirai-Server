package net.lz1998.pbbot.method;


import net.lz1998.pbbot.constant.Constant;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Java正则表达式过滤出字母、数字和中文
 * from https://blog.csdn.net/sx_1706/article/details/118735599
 *
 * 过滤出字母的正则表达式
 * [^(A-Za-z)]
 * 过滤出 数字 的正则表达式
 * [^(0-9)]
 * 过滤出 中文 的正则表达式
 * [^(\\u4e00-\\u9fa5)]
 * 过滤出字母、数字和中文的正则表达式
 * [^(a-zA-Z0-9\\u4e00-\\u9fa5)]
 */
@Component
public class FilterChar {

    public static void main(String[] args) {
//        String string = new String(" 士f夫 查询 夏洛 特d色");
//        String string = new String(" qr  夫查询夏洛特d色");
//        System.out.println(new FilterChar().filterChinese(string));
    }
    /**
     * @Title : filterChinese
     * @Type : FilterStr
     * @Description : 过滤出中文
     * @param chin
     * @return
     */
    public String filterChinese(String chin)
    {
        chin = chin.replaceAll("(查询)|[^\\u4e00-\\u9fa5]", "");
        return chin;
    }

    /**
     *
     * @Title : filterNumber
     * @Type : FilterStr
     * @Description : 过滤出数字
     * @return
     */
    public static String filterNumber(String number)
    {
        number = number.replaceAll("[^(0-9)]", "");
        return number;
    }

    /**
     *
     * @Title : filterAlphabet
     * @Type : FilterStr
     * @Description : 过滤出字母和
     * @param alph
     * @return
     */
    public static String filterAlphabet(String alph)
    {

        alph = alph.replaceAll("[^(A-Za-z)]", "");
        return alph;
    }

    /**
     *
     * @Title : filter
     * @Type : FilterStr
     * @Description : 过滤出字母、数字和中文
     * @param character
     * @return
     */
    public static String filter(String character)
    {
        character = character.replaceAll("[^(a-zA-Z0-9\\u4e00-\\u9fa5)]", "");
        return character;
    }
}
