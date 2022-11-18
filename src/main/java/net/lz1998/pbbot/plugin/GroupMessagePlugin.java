package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Charcater;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.constant.Constant;
import net.lz1998.pbbot.method.FilterChar;
import net.lz1998.pbbot.method.JsoupParseHtml;
import net.lz1998.pbbot.method.JudgeMessagePrefix;
import net.lz1998.pbbot.method.MatchDirctionaryName;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.jsoup.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GroupMessagePlugin extends BotPlugin {

    @Autowired
    private JudgeMessagePrefix judgeMessagePrefix;
    @Autowired
    private FilterChar filterChar;
    @Autowired
    private MatchDirctionaryName dirctionaryName;
    @Autowired
    private JsoupParseHtml parseHtml;


    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        //收到群消息时调用此方法
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        String rawMessage = event.getRawMessage();
//        String messStr = "  rq 夏洛特";

        boolean tokenPrefix = judgeMessagePrefix.groupMessagePrefix(rawMessage);
        //如果带前缀查询标志
        if (tokenPrefix) {
            //获取中文字符串
            String chinese = filterChar.filterChinese(rawMessage);
            //匹配字典名称
            String charcaterName = dirctionaryName.matchDictionName(chinese);
            if (!StringUtil.isBlank(charcaterName)) {
                Charcater charcater = parseHtml.parseImageUrl(charcaterName);

                //发送群消息
                Msg msg = Msg.builder().image(charcater.getImageUrl())
                        .at(userId)
                        .share(Constant.Wiki_Path + charcater.getName(),
                                charcater.getTitle(), charcater.getContent(), charcater.getImageUrl())
                        .sendToGroup(bot, groupId);
            }

        }


        /*if (messStr.startsWith("rq") && messStr.contains("夏洛特")) {
            Charcater charcater = JsoupParseHtml.parseImageUrl("");
            Msg msg = Msg.builder().image(charcater.getImageUrl())
                    .share("https://wiki.biligame.com/cq/" + charcater.getName(),
                            charcater.getTitle(), charcater.getContent(), charcater.getImageUrl())
                    .at(userId)
                    .sendToGroup(bot, groupId);

//            .share("https://wiki.biligame.com/cq/四天王夏洛特",
//            "title","content",
//            "https://patchwiki.biligame.com/images/cq/f/fd/10gzz5kggdkbumd6hys5cny986ikn64.png")
//            bot.sendGroupMsg(groupId, msg, false);
        }*/
        return MESSAGE_BLOCK;
    }

}
