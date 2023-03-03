package net.lz1998.pbbot.strategy;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.utils.Msg;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(3)
public class TipMessageProcessor extends MessageProcessor {

    public TipMessageProcessor() {
        this.command = "(^\\s*tip)|(^\\s*命令)";
    }

    @Override
    public Msg process(String message) {
        return Msg.builder()
                .text("关键字：勇士登场期数参考表,特殊技能, 精淬武器, 戒指, 服装, 符文, 女神, 领主,全力战,活动日历," +
                        "Buff的叠加方式,原画图鉴,游戏壁纸,CQ表情包,CQ联动史,CQ玩家用语集,勇士别名一览,过场画面,游戏图标一览,周年纪念册\n" +
                        "请输入例如qr浦西或者查询浦西");
    }
}
