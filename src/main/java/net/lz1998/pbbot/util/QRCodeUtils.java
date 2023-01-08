package net.lz1998.pbbot.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.beans.factory.annotation.Value;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

/**
 * 工具类之十七 基于zxing的二维码生成工具类
 * https://blog.csdn.net/weixin_44774463/article/details/114259430
 * <p>
 * ## with logo
 * https://acsolutions.lu/blog/create-a-qrcode-with-your-logo-for-a-business-card/668/
 */
//@PropertySource("classpath:application.yml")
public class QRCodeUtils {
    private final static int width = 300;
    private final static int height = 300;
    private final static String format = "png";

    //二维码添加logo
    public static BufferedImage generateQRCodeWithLogo(String msg, BufferedImage logo) {
        BufferedImage qrCodeImage = generateQRCode(msg);
        try {
            combinedQrCodeWithLogo(qrCodeImage, logo);
            return qrCodeImage;
        } catch (Exception e) {
            throw new RuntimeException("生成二维码错误");
        }

    }

    /**
     * 二维码实现
     *
     * @param msg /二维码包含的信息
     */
    public static BufferedImage generateQRCode(String msg) {
        //定义二维码参数
        HashMap hashMap = new HashMap<>();
        //设置编码 EncodeHintType类中可以设置MAX_SIZE， ERROR_CORRECTION，CHARACTER_SET，DATA_MATRIX_SHAPE，AZTEC_LAYERS等参数
        hashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        //定义二维码纠错等级
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashMap.put(EncodeHintType.MARGIN, "1");
        try {
            //生成二维码
            BitMatrix bitMatrix = new MultiFormatWriter().encode(msg, BarcodeFormat.QR_CODE, width, height, hashMap);
//            MatrixToImageWriter.writeToStream(bitMatrix, format, ous);
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            // 创建BufferedImage将二维码图片绘制到里面， MatrixToImageWriter.toBufferedImage方法返回的BufferedImag.Type为TYPE_BYTE_BINARY，既只有黑白两种颜色
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            //write qr code to new image at position 0,0
            bufferedImage.getGraphics().drawImage(MatrixToImageWriter.toBufferedImage(bitMatrix), 0, 0, null);

            return bufferedImage;
        } catch (Exception e) {
            throw new RuntimeException("二维码生成失败");
        }
    }

    /**
     * https://www.apiref.com/java11-zh/java.desktop/java/awt/Graphics2D.html
     * Java 2D允许分配透明(alpha)值，以便底层的图形可以显示出来。在处理不透明时，通常会使用AlphaComposite.SRC_OVER。透明值由透明到不透明是在0.0和1.0之间
     * AlphaComposite.DST   - 目标未修改。
     * AlphaComposite.DST_ATOP   - 目标和源重叠的部分组合在源上。
     * AlphaComposite.DST_IN   - 显示目标和源重叠的部分。
     * AlphaComposite.DST_OUT   -显示目标没有和源重叠的部分。
     * AlphaComposite.DST_OVER   - 目标覆盖在源之上。
     * AlphaComposite.SRC   - 源复制给目标。
     * AlphaComposite.SRC_ATOP   - 源和目标重叠的部分组合在目标上。
     * AlphaComposite.SRC_IN   - 显示源和目标重叠的部分。
     * AlphaComposite.SRC_OUT   - 显示源没有和目标重叠的部分。
     *
     * @param qrImage
     */
    private static void combinedQrCodeWithLogo(BufferedImage qrImage, BufferedImage logoImage) {
        Graphics2D graphics = (Graphics2D) qrImage.getGraphics();
        //write qr code to new image at position 0/0
//        graphics.drawImage(qrImage, 0, 0, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //write logo into combined image at position deltaWidth/2 deltaHeight/2
        // Calculate the delta height and width between QR code and logo
        int deltaWidth = qrImage.getWidth() - logoImage.getWidth();
        int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
        graphics.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

        logoImage.flush();
        qrImage.flush();
        //写入本地文件
        /*try {
            ImageIO.write(combinedImg,"png",os);
            //store Image
            Files.copy(new ByteArrayInputStream(os.toByteArray()),
                    Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    //resize logo图片 使其比二维码 小
    //eg. resize(logoImg, qrImage.getHeight() / 4, qrImage.getWidth() / 4);
    private static BufferedImage resize(BufferedImage logoImg, int height, int width) {
        Image tmp = logoImg.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

    // 群头像地址  //p.qlogo.cn/gh/615621024/615621024/40
    private static BufferedImage getAvatar(Long qq) throws IOException {
        URL url = new URL("https://q2.qlogo.cn/headimg_dl?dst_uin=" + qq.toString() + "&spec=3");
        return ImageIO.read(url);
    }

}
