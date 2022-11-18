package net.lz1998.pbbot.constant;

public class Constant {
    public static final String etc = "...";

    public static final String Query_prefix = "(^\\s*qr)|(^\\s*查询)";

    // 分割符为：逗号, {,}, 空白符
//    public static final String Query_split = "[,\\{\\}\\s]";
//    分割 1个或多个空格 或者 . ,；
    public static final String Split_regx = "\\s+|\\;|\\；";
    public static final int Thirty = 30;

    public static final String TXT_Path= "E:\\JavaProject\\Spring-Mirai-Server-master\\src\\main\\resources\\static\\CharacterNameDictionary.txt";
    public static final String Wiki_Path= "https://wiki.biligame.com/cq/";

    public static final String[] characterName = { "紫色的传令 艾丽丝","四天王夏洛特"};
}
