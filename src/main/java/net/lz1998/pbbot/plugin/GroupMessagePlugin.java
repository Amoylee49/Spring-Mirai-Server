package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Charcater;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.method.JsoupParseHtml;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GroupMessagePlugin extends BotPlugin {

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        //收到群消息时调用此方法
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        String rawMessage = event.getRawMessage();

        String messStr = "rq 夏洛特";
        if (messStr.startsWith("rq") && messStr.contains("夏洛特")) {
            Charcater charcater = JsoupParseHtml.parseImageUrl("");
            Msg msg = Msg.builder().image(charcater.getImageUrl())
                    .share("https://wiki.biligame.com/cq/" + charcater.getName(),
                            charcater.getTitle(), charcater.getContent(), charcater.getImageUrl())
                    .at(userId)
                    .sendToGroup(bot, 562649608);

//            .share("https://wiki.biligame.com/cq/四天王夏洛特",
//            "title","content",
//            "https://patchwiki.biligame.com/images/cq/f/fd/10gzz5kggdkbumd6hys5cny986ikn64.png")
//            bot.sendGroupMsg(groupId, msg, false);
        }
        return MESSAGE_BLOCK;
    }

}
