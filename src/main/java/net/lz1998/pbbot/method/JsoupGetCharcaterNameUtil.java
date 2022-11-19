package net.lz1998.pbbot.method;

import net.lz1998.pbbot.constant.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 只运行一次，数据是append 到 txt 文件的
 */
public class JsoupGetCharcaterNameUtil {

    public static void main(String[] args) {
//        getName("");
//        try {
//            charcaterNameTxT(new StringBuilder());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        getNameAllType();
    }

    //所有类型的 人物数据
    public static void getNameAllType() {
        String[] characterType = Constant.characterType;
        for (String type : characterType) {
            String url = Constant.Wiki_Path + type;
            getName(url, type);
        }
    }


    public static void getName(String url,String type) {
//        url = "https://wiki.biligame.com/cq/剑士";

        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        Elements elements = document.getElementsByClass("img");
        int i = 0;
        for (Element e : elements) {
            i++;
            String attr = e.select("a").attr("title");
//            System.out.println(attr);
                builder.append(attr).append(";");
            if (i%5 ==0)
                builder.append("\n");

        }
        System.out.println(type +"总人物有几个："+ i );

        try {
            charcaterNameTxT(builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将Stringbuilder 的数据写入txt文件
    public static void charcaterNameTxT(StringBuilder stringBuilder) throws IOException {
//        Files.deleteIfExists(Paths.get("hello.txt"));
//        Files.write(Paths.get("hello.txt"), "你好hi".getBytes(Charset.forName("UTF-8")));
//        log.info("bytes:{}", Hex.encodeHexString(Files.readAllBytes(Paths.get("hello.txt"))).toUpperCase());

//        StringBuilder builder = new StringBuilder("测试数sdffsdf据").append(";");
        String filePath = Constant.TXT_Path;
//        Path file = Files.createFile(Paths.get(filePath));
        if (stringBuilder.length() > 0){
            Files.write(Paths.get(filePath), stringBuilder.toString().getBytes((Charset.forName("UTF-8"))),
                    StandardOpenOption.APPEND);
        }

    }
    //Java8
    void testCreateFile1() throws IOException {
        String fileName = "D:\\data\\test\\newFile.txt";

        Path path = Paths.get(fileName);
        // 使用newBufferedWriter创建文件并写文件
        // 这里使用了try-with-resources方法来关闭流，不用手动关闭
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            writer.write("Hello World -创建文件!!");
        }

        //追加写模式
        try (BufferedWriter writer =
                     Files.newBufferedWriter(path,
                             StandardCharsets.UTF_8,
                             StandardOpenOption.APPEND)){
            writer.write("Hello World -字母哥!!");
        }
    }

    //Java7
    void testCreateFile2() throws IOException {
        String fileName = "D:\\data\\test\\newFile2.txt";

        // 从JDK1.7开始提供的方法
        // 使用Files.write创建一个文件并写入
        Files.write(Paths.get(fileName),
                "Hello World -创建文件!!".getBytes(StandardCharsets.UTF_8));

        // 追加写模式
        Files.write(
                Paths.get(fileName),
                "Hello World -字母哥!!".getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.APPEND);
    }

}
