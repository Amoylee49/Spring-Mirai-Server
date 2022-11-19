package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.constant.Constant;
import net.lz1998.pbbot.method.JudgeMessagePrefix;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupTipPlugin extends BotPlugin {

    @Autowired
    private JudgeMessagePrefix judgeMessagePrefix;

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        //收到群消息时调用此方法
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        String rawMessage = event.getRawMessage();
        //如果是tip命令 发一张tip提示图片
        boolean tipFlag = judgeMessagePrefix.tipMessage(rawMessage);

        if (tipFlag) {
            Msg.builder().image(Constant.Tip_Path)
//                          .at(userId)
                          .sendToGroup(bot, groupId);
        }
        return MESSAGE_IGNORE;
    }
}
