package net.lz1998.pbbot.method;

import net.lz1998.pbbot.constant.Constant;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

/**
 * Java---中文词匹配 正向、逆向和双向最大匹配算法
 * 参考 https://blog.csdn.net/weixin_38178627/article/details/126885308
 */
@Component
public class MatchDirctionaryName {
    //最大字典长度
    private static final int MAX_LENGTH = 5;

    //载入字典和自定义添加词
    private static HashSet<String> dictionary;

    public static void main(String[] args) {
//        new MatchDirctionaryName().getDictionary();
//        String str = "千斤什莉";
        String str = "你好";
//        System.out.println(getDictionName(str));
//        System.out.println(leftMax(str));
//        rightMax("qr夏洛特");
//        System.out.println(new MatchDirctionaryName().matchDictionName("士大夫士大夫夏洛"));
        System.out.println("done");
    }

    /**
     * 正向最大匹配
     *
     * @return
     */
    @NotNull
    public static List<String> leftMax(String str) {

        List<String> result = new ArrayList<String>();
        String input = str;

        if (input.length() > 0) {
            String subSeq;
            // 每次取小于或者等于最大字典长度的子串进行匹配
            if (input.length() <= MAX_LENGTH)
                subSeq = input;
            else
                subSeq = input.substring(0, MAX_LENGTH);

            while (subSeq.length() > 0) {
                // 如果字典前缀模糊匹配   中含有该子串或者子串颗粒度为1，子串匹配成功
                String finalSubSeq = subSeq;
                boolean prefixFlag = dictionary.stream().filter(s -> s.startsWith(finalSubSeq)).anyMatch(s -> true);
                if (prefixFlag) {
                    result.add(subSeq);
                    break; // 退出循环，进行下一次匹配
                } else {
                    // 去掉匹配字段最后面的一个字
                    subSeq = subSeq.substring(0, subSeq.length() - 1);
                }
            }

        }
        return result;
    }

    /**
     * 逆向最大匹配
     */
    public static List<String> rightMax(String str) {
        // 采用堆栈处理结果，后进先出
//        Stack<String> store = new Stack<String>();
        List<String> results = new ArrayList<String>();
        String input = str;

        if (input.length() > 0) {

            String subSeq;
            // 每次取小于或者等于最大字典长度的子串进行匹配
            if (input.length() <= MAX_LENGTH)
                subSeq = input;
            else
                subSeq = input.substring(input.length() - MAX_LENGTH);

            while (subSeq.length() > 0) {
                // 字典从后往前匹配或者子串颗粒度为1，子串匹配成功
                String finalSubSeq = subSeq;
                boolean endFlag = dictionary.stream().filter(s -> s.endsWith(finalSubSeq)).anyMatch(s -> true);
                if (endFlag) {
                    results.add(subSeq);
                    break;
                } else {
                    // 去掉匹配字段最前面的一个字
                    subSeq = subSeq.substring(1);
                }
            }
        }
        return results;
    }

    /**
     * 双向匹配中文字符串，返回字典中匹配的人物名称
     *
     * @param messageChar
     * @return
     */
    public String matchDictionName(String messageChar) {
        //获取字典
        getDictionary();

        List<String> leftMax = leftMax(messageChar);
        List<String> rightMax = rightMax(messageChar);
        Optional<String> characterName = Optional.empty();
        if (!leftMax.isEmpty()) {
            //如果结果集不为空，获取字典中的匹配
            String leftStr = leftMax.get(0);
            Optional<String> dirName = dictionary.stream().filter(s -> s.startsWith(leftStr)).findAny();
            characterName = dirName;
        } else if (!rightMax.isEmpty()) {
            //如果结果集不为空，获取字典中的匹配
            String rightStr = rightMax.get(0);
            Optional<String> dirName = dictionary.stream().filter(s -> s.endsWith(rightStr)).findAny();
            characterName = dirName;
        }
        return characterName.isPresent() ? characterName.get() : "";
    }

    // 初始化字典，采用 hashset 存储
    public void getDictionary() {
        dictionary = new HashSet<String>();
        String dicpath = Constant.TXT_Path;
        String line = null;
        BufferedReader br;
        try {
            // 按照 UTF-8 编码读入文件
            br = new BufferedReader(new InputStreamReader(new FileInputStream(dicpath), "UTF-8"));
            try {
                while (((line = br.readLine()) != null)) {
                    // 按照空格切分
                    String[] str = line.split(Constant.Split_regx);
                    for (int i = 0; i < str.length; i++) {
                        line = str[i];
                        dictionary.add(line);
                    }
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
