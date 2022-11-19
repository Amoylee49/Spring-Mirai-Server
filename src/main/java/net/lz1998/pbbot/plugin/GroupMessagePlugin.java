package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.strategy.MessageProcessor;
import onebot.OnebotBase;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GroupMessagePlugin extends BotPlugin {

    @Autowired
    private List<MessageProcessor> messageProcessors;

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        long groupId = event.getGroupId();
        String rawMessage = event.getRawMessage();

        for (MessageProcessor messageProcessor : messageProcessors) {
            if (messageProcessor.isMatch(rawMessage)) {
                List<OnebotBase.Message> messages = messageProcessor.process(rawMessage);
                if (messages != null) {
                    bot.sendGroupMsg(groupId, messages, false);
                    return MESSAGE_BLOCK;
                }
            }
        }
        return MESSAGE_BLOCK;
    }
}
