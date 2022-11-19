package net.lz1998.pbbot.constant;

public class Constant {
    public static final String etc = "...";

    public static final String Query_prefix = "(^\\s*qr)|(^\\s*查询)";
    public static final String Tip_regx = "(^\\s*tip)|(^\\s*命令)";

    // 分割符为：逗号, {,}, 空白符
//    public static final String Query_split = "[,\\{\\}\\s]";
//    分割 1个或多个空格 或者 . ,；
    public static final String Split_regx = "\\;|\\；";
    public static final int Thirty = 30;

    public static final String TXT_Path= "E:\\JavaProject\\Spring-Mirai-Server-master\\src\\main\\resources\\static\\CharacterNameDictionary.txt";
    public static final String Tip_Path= "E:\\JavaProject\\Spring-Mirai-Server-master\\src\\main\\resources\\static\\tipNew.jpeg";
    public static final String Wiki_Path= "https://wiki.biligame.com/cq/";
    public static final String Wiki_Logo_Path = "https://patchwiki.biligame.com/resources/assets/images/logo/logo_cq.png";

//    public static final String[] characterName = { "紫色的传令 艾丽丝","四天王夏洛特"};
    public static final String[] characterType = { "剑士","骑士","弓手","猎人","法师","祭司"};
    public static final String[] specialType = { "勇士登场期数参考表","特殊技能","精淬武器","戒指","服装","符文","女神","领主",
                                "灵魂要塞","活动日历"};
//    https://patchwiki.biligame.com/resources/assets/images/logo/logo_cq.png
}
