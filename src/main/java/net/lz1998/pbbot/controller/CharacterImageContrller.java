package net.lz1998.pbbot.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@RestController
public class CharacterImageContrller {
    // 如果需要返回BufferedImage，必须有下面的converter。如果没有converter只能返回[]byte。
//    @Bean
//    public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
//        return new BufferedImageHttpMessageConverter();
//    }

    //生成图片
    @RequestMapping(value = "/getImage2", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public BufferedImage getImage(@RequestParam(defaultValue = "绷带艾丽") String characterName) throws IOException {
        //创建400*300图片
        BufferedImage bufferedImage = new BufferedImage(210, 180, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = bufferedImage.createGraphics();
        //背景填充
        graphics.setColor(Color.black);
        graphics.fillRect(20, 30, bufferedImage.getWidth(), bufferedImage.getHeight());

        //写 人物名称， 黑色字体 字号30
        graphics.setFont(new Font(null, Font.PLAIN, 20));
        graphics.setColor(Color.RED);
        graphics.drawString(characterName, 105, 30);

        //画图像
        graphics.drawImage(getAvatar(), 0, 0, null);
        return bufferedImage;
    }

    // 获取头像
    public BufferedImage getAvatar() throws IOException {
//        URL url = new URL("https://patchwiki.biligame.com/images/cq/6/6a/o5vj07u2fed3b71swd46hw36ir296mk.png");
        URL url = new URL("https://patchwiki.biligame.com/images/cq/f/fd/10gzz5kggdkbumd6hys5cny986ikn64.png");
        return ImageIO.read(url);
    }

}
