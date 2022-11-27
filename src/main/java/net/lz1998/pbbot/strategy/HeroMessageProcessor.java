package net.lz1998.pbbot.strategy;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.Character;
import net.lz1998.pbbot.bean.CharacterHolder;
import net.lz1998.pbbot.method.CharacterCaches;
import net.lz1998.pbbot.util.StringUtils;
import net.lz1998.pbbot.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
@Order(2)
public class HeroMessageProcessor extends MessageProcessor {

    @Autowired
    private CharacterCaches characterCaches;

    public HeroMessageProcessor() {
        this.command = "(^\\s*qr)|(^\\s*查询)";
    }

    @Override
    public Msg process(String message) {
        String parameter = getCommandParameters(message).get(0);
        if (StringUtils.isEmpty(parameter)) {
            return null;
        }
        List<CharacterHolder> allCharacters = characterCaches.getAllCharacters();
        for (CharacterHolder characterHolder : allCharacters) {
            List<String> nickNames = characterHolder.getNickNames();
            for (String nickName : nickNames) {
                if (nickName.startsWith(parameter) || nickName.endsWith(parameter)) {
                    Character character = characterHolder.getCharacter();
                    return Msg.builder()
                            .text(character.getName() + "\n" + character.getPageUrl());
                }
            }
        }
        return null;
    }

}
