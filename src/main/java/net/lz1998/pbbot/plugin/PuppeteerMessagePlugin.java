package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Character;
import net.lz1998.pbbot.bean.CharacterHolder;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import net.lz1998.pbbot.method.CharacterCaches;
import net.lz1998.pbbot.strategy.MessageProcessor;
import net.lz1998.pbbot.util.StringUtils;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Component
public class PuppeteerMessagePlugin extends BotPlugin {
    @Autowired
    private ScreenshotProcess processor;

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        long userId = event.getUserId();
        long groupId = event.getGroupId();
        String rawMessage = event.getRawMessage();

        String heroName = processor.heroName(rawMessage);

        if (StringUtils.isNotEmpty(heroName)) {
//            log.info("英雄人物名称:{}",heroName);
            try {
                String encode = URLEncoder.encode(heroName, "utf-8");
                Msg.builder()
                        .image(String.format("http://localhost:3000/puppe?heroName=%s", encode))
                        .sendToGroup(bot, groupId);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return MESSAGE_IGNORE;
    }

}

@Component
class ScreenshotProcess extends MessageProcessor {

    @Autowired
    private CharacterCaches character;

    public ScreenshotProcess() {
        this.command = "(^\\s*ss)|(^\\s*截图)";
    }

    public String heroName(String message) {
        boolean match = isMatch(message);
        if (match) {
            String parameters = getCommandParameters(message).get(0);
            if (StringUtils.isNotEmpty(parameters)) {
                List<CharacterHolder> allCharacters = character.getAllCharacters();
                for (CharacterHolder characterHolder : allCharacters) {
                    List<String> nickNames = characterHolder.getNickNames();
                    for (String nickName : nickNames) {
                        if (nickName.startsWith(parameters) || nickName.endsWith(parameters)) {
                            Character character = characterHolder.getCharacter();
                            return character.getName();
                        }
                    }
                }
            }
        }
        return "";
    }

    @Override
    public Msg process(String message) {
        return null;
    }
}