package net.lz1998.pbbot.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Slf4j
/**
 * by https://www.cnblogs.com/dong320/p/16385266.html
 */
public class BufferImageUtil {
    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("E:\\JavaProject\\Spring-Mirai-Server-master\\src\\main\\resources\\static\\tip3.jpeg"));
//        String str = "      Little Tip: 🔈  还有以下关键词可以查询，块冻冻你的小手吧 — > " +"\"勇士\","+
//                "\"特殊技能\",\"精淬武器\",\"戒指\",\"服装\",\"符文\",\"女神\",\"领主\",\n" +
//                "\"副本\",\"活动日历\"!!";
        String str = "      Little Tip: 🔈  还有以下关键词可以查询，块冻冻你的小手吧 —> " +"勇士,"+
                "特殊技能,精淬武器,戒指,服装  符文,女神,领主,要塞,活动日历!!!";
        // 指定区域，左对齐
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setFont(new Font(null, Font.CENTER_BASELINE, 17));
        graphics.setColor(Color.WHITE);
//        graphics.setColor(new Color(205, 104, 16));
        drawString(graphics, str, 25, 50, 260, 250);
        ImageIO.write(bufferedImage, "png", new File("E:\\JavaProject\\Spring-Mirai-Server-master\\src\\main\\resources\\static\\tipNew3.jpeg"));
    }

    /**
     * 在指定区域写文字，超过长度自动换行
     *
     * @param g2d       Graphics2D类
     * @param text      需要写的字符串
     * @param x         第一行文字x坐标
     * @param y         第一行文字y坐标
     * @param maxWidth  一行最大宽度
     * @param maxHeight 文本可展示的最大高度，如果行数过多，会自动缩小字体
     * @return 行数
     */
    public static TextInfoDto drawString(Graphics2D g2d, String text, int x, int y, int maxWidth, int maxHeight) {
        TextInfoDto textInfoDto = new TextInfoDto();

        // 根据规定的最大宽度与高度，自动缩小字体
        fitFontSize(g2d, text, maxWidth, maxHeight);

        FontMetrics fontMetrics = g2d.getFontMetrics();
        // 字符串高、宽
        int textHeight = fontMetrics.getHeight();
        int textWidth = fontMetrics.stringWidth(text);

        textInfoDto.setWidth(textWidth);
        textInfoDto.setHeight(textHeight);

        int lineCount = 0;
        String tempText = text;
        while (textWidth > maxWidth) {
            // 用每个字的宽度累加，计算一行需要多少字
            int lineStrWidth = 0;
            int charCount = 0;
            while (lineStrWidth < maxWidth) {
                lineStrWidth += fontMetrics.charWidth(tempText.charAt(charCount));
                charCount++;
            }

            String drawText = tempText.substring(0, charCount);
            // 本行文本宽度
            int subTxtWidth = fontMetrics.stringWidth(drawText);
            // 如果字数长度不够就加点
            while (subTxtWidth < maxWidth && charCount < tempText.length()) {
                charCount++;
                drawText = tempText.substring(0, charCount);
                subTxtWidth = fontMetrics.stringWidth(drawText);
            }
            // 如果字数长度超了就减点
            while (subTxtWidth > maxWidth) {
                charCount--;
                drawText = tempText.substring(0, charCount);
                subTxtWidth = fontMetrics.stringWidth(drawText);
            }
            lineCount++;
            g2d.drawString(drawText, x, y);
            if (charCount >= tempText.length()) {
                tempText = "";
                break;
            }
            y += textHeight;
            textWidth = textWidth - subTxtWidth;
            tempText = tempText.substring(charCount);
        }
        if (StringUtils.isNotBlank(tempText)) {
            // 本行长度小于最大宽度，或者最后一行剩下的几个字
            g2d.drawString(tempText, x, y);
            lineCount++;
        }

        textInfoDto.setLineCount(lineCount);
        log.info("drawString,textInfoDto={}", textInfoDto);
        return textInfoDto;
    }

    /**
     * 根据规定的最大宽度与高度，自动缩小字体
     */
    public static void fitFontSize(Graphics2D g2d, String text, int maxWidth, int maxHeight) {
        FontMetrics fontMetrics = g2d.getFontMetrics();
        // 字符串高、宽
        int textHeight = fontMetrics.getHeight();
        int textWidth = fontMetrics.stringWidth(text);

        // 当前字体，写全部字大概需要多少行
        int tmpLineCount = textWidth / maxWidth;
        if (textWidth % maxWidth > 0) {
            tmpLineCount++;
        }
        log.info("原字fontSize=" + fontMetrics.getFont().getSize());
        // 修改字体大小
        while (textHeight * tmpLineCount > maxHeight) {
            Font font = g2d.getFont();
            // 太小就看不清了
            if (font.getSize() <= 8) {
                break;
            }
            g2d.setFont(new Font(font.getName(), font.getStyle(), font.getSize() - 1));

            // 重新获取字体宽高
            fontMetrics = g2d.getFontMetrics();
            textHeight = fontMetrics.getHeight();
            textWidth = fontMetrics.stringWidth(text);

            tmpLineCount = textWidth / maxWidth;
            if (textWidth % maxWidth > 0) {
                tmpLineCount++;
            }
        }
        log.info("修改后fontSize=" + g2d.getFont().getSize());
    }


}

    @Data
    class TextInfoDto {

    //        @ApiModelProperty("文字高度")
    private Integer height;

    //        @ApiModelProperty("文字宽度")
    private Integer width;

    //        @ApiModelProperty("换行行数")
    private Integer lineCount;

//        @Override
//        public String toString() {
////            return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
//        }
    }
