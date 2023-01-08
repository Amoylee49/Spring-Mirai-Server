package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class QRCodeMessagePlugin extends BotPlugin {
    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        long groupId = event.getGroupId();
        long userId = event.getUserId();
        String rawMessage = event.getRawMessage().trim();
        if ("qrcode".equalsIgnoreCase(rawMessage)){
            Msg.builder()
                    .image(String.format("http://localhost:8081/getQRCodeImage?qq=%d&groupId=%d",userId,groupId))
                    .sendToGroup(bot, groupId);
        }
        return MESSAGE_IGNORE;
    }
}
