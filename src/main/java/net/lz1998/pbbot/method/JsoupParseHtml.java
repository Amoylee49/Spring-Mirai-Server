package net.lz1998.pbbot.method;


import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Character;
import net.lz1998.pbbot.constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JsoupParseHtml {

    /**
     * 获取人物图片链接 image
     * Jsoup 选择器 参考 https://blog.csdn.net/zyb418/article/details/123283644
     * @param name
     * @return
     */
    public static Character parseImageUrl(String name) {
//        name = "水之神女 海琅";
        Document document = null;
        try {
            document = Jsoup.connect(Constant.Wiki_Path + name).get();
        } catch (IOException e) {
            log.info("获取不到人物链接");
        }
        //图片地址
        Elements cqhero_img = document.getElementsByClass("cqhero_img");
//        String img = cqhero_img.attr("img");
        String src = cqhero_img.select("img").attr("src");

        //获取名字
        String title = document.title();

        //输出内容
//        Elements cqframe_box = document.getElementsByClass("cqframe_box");
        Elements content = document.select("div.hero_story");

        String text = content.text();
        if (src.isEmpty()) {
            src = Constant.Wiki_Logo_Path;
        }
        if (text.isEmpty()) {
            text = document.select("div.quote-box").text();
        }
        if (text.length() > Constant.Thirty) {
            text = text.substring(0, Constant.Thirty) + Constant.etc;
        }

//        select(“div.masthead”).first();
        Character character = new Character();
        character.setImageUrl(src);
        character.setName(name);
        character.setTitle(title);
        character.setContent(text);
        return character;
    }

    public static void main(String[] args) {
        parseImageUrl(null);
    }

}
