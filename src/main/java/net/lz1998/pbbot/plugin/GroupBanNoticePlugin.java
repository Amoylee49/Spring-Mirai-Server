package net.lz1998.pbbot.plugin;

import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class GroupBanNoticePlugin extends BotPlugin {

    @Override
    public int onGroupBanNotice(@NotNull Bot bot, @NotNull OnebotEvent.GroupBanNoticeEvent event) {
        return super.onGroupBanNotice(bot, event);
    }
}
