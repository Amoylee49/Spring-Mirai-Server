package net.lz1998.pbbot.strategy;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotBase;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@Order(3)
public class TipMessageProcessor extends MessageProcessor {

    public TipMessageProcessor() {
        this.command = "(^\\s*tip)|(^\\s*命令)";
    }

    @Override
    public List<OnebotBase.Message> process(String message) {
        return Msg.builder()
                .text("支持勇士，勇士登场期数参考表，特殊技能，精淬武器，戒指，服装，符文，女神，领主，灵魂要塞，活动日历等wiki查询。\n" +
                        "请输入例如qr浦西或者查询浦西")
                .build();
    }
}
