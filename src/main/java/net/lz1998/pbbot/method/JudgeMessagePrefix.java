package net.lz1998.pbbot.method;

import net.lz1998.pbbot.constant.Constant;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JudgeMessagePrefix {

    public static void main(String[] args) {
//        System.out.println(groupMessagePrefix(""));
//        filterMessageByRegx("");
    }

    /**
     * 判断群消息是否带查询标志
     * @param message
     * @return
     */
    public boolean groupMessagePrefix(String message) {
//        message = " qr夏洛  特";

        //正则解析查询标志 CASE_INSENSITIVE 忽略大小写
        Matcher matcher = Pattern.compile(Constant.Query_prefix,Pattern.CASE_INSENSITIVE)
                .matcher(message);
        //lookingAt();
//        System.out.println(matcher.lookingAt());
        return matcher.find();
    }

    /**
     * 判断群消息是否是请求提示
     * @param tipMessage
     * @return
     */
    public boolean tipMessage(String tipMessage){
        Matcher matcher = Pattern.compile(Constant.Tip_regx,Pattern.CASE_INSENSITIVE)
                .matcher(tipMessage);
        return matcher.find();
    }


    /**
     * 过滤带查询标志的message
     *
     * @param groupMessage
     * @return
     */
   /* public static String filterMessageByRegx(String groupMessage) {
        groupMessage = "   qr 夏洛  特";

        String[] groupMesNew = groupMessage.trim().toLowerCase().split(Constant.Split_regx);

        //正则解析查询标志
        Pattern pattern = Pattern.compile(Constant.Query_prefix);
        Matcher matcher = pattern.matcher(groupMessage);
        if (matcher.find()) {
            List<String> newStrings = Stream.of(groupMesNew).flatMap(s -> {
                String[] split = s.split(Constant.Query_prefix);
                Stream<String> stream = Arrays.stream(split);
                return stream;
            }).collect(Collectors.toList());

            System.out.println(newStrings);
//          String[] split = Arrays.toString(groupMesNew).split(Constant.Query_prefix);
        } else {
            System.out.println("not found");
        }

        return "";
    }*/

  /*  private void getStr(String input){
        Stream<Object> booleanStream = Stream.of(input).map(s -> {
            String[] split = s.split(Constant.Split_regx);
            Stream<String> res = Arrays.stream(split);
            boolean booleanQuery;
            if (res.map(String::toLowerCase)
                    .anyMatch(s1 -> s1.matches(Constant.Query_prefix))) booleanQuery = true;
            else booleanQuery = false;
            return booleanQuery ? "done" : Optional.empty();
        });
    }*/

//        boolean booleanStream = true;
//        //如果包含查询标志
//        Optional<String> queryResult = res.filter(s1 -> !s1.isEmpty())
//                .skip(1).findFirst();
//        return booleanQuery ? queryResult : Optional.empty();


//        Stream<String> stream =
//                Stream.of("d2", "a2", "b1", "b3", "c")
//                        .filter(s -> s.startsWith("a"));
//
//        stream.anyMatch(s -> true);  // The Stream has been used and is now consumed.
//        stream.noneMatch(s -> true); // IllegalStateException; stream was already used

}
