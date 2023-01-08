package net.lz1998.pbbot.controller;

import net.lz1998.pbbot.util.QRCodeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

@RestController
public class QRCodeImageController {

    // 如果需要返回BufferedImage，必须有下面的converter。如果没有converter只能返回[]byte。
    @Bean
    public BufferedImageHttpMessageConverter bufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    @RequestMapping(value = "/getQRCodeImage", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public BufferedImage getQRCodeImage(@RequestParam(defaultValue = "1000") Long qq, @RequestParam(defaultValue = "123") Long groupId) throws IOException{
        //qq头像
        BufferedImage avatar = getAvatar(qq);
        //二维码信息
        String msg = "QQ号："+qq.toString();
        BufferedImage bufferedImage = QRCodeUtils.generateQRCodeWithLogo(msg, avatar);
        return bufferedImage;
    }

    // 获取QQ头像  //p.qlogo.cn/gh/615621024/615621024/40
    public BufferedImage getAvatar(Long qq) throws IOException {
        URL url = new URL("https://q2.qlogo.cn/headimg_dl?dst_uin=" + qq.toString() + "&spec=3");
        return ImageIO.read(url);
    }

    //获取群头像  有40*40 100*100 140*140 url数字更改
//    https://qun.qq.com/member.html  群管理地址
    public BufferedImage getGroupAvatar(Long groupId) throws IOException {
        String imageUrl = String.format("https://p.qlogo.cn/gh/%d/%d/100", groupId, groupId);
        URL url = new URL(imageUrl);
        return ImageIO.read(url);
    }

}
