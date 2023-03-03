package net.lz1998.pbbot.strategy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.constant.Constant;
import net.lz1998.pbbot.util.StringUtils;
import net.lz1998.pbbot.utils.Msg;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.URLEncoder;

import static net.lz1998.pbbot.constant.Constant.WIKI_PATH;

@Component
@Slf4j
@Order(1)
public class NormalUrlMessageProcessor extends MessageProcessor {
    public final String[] specialType = {"勇士登场期数参考表", "特殊技能", "精淬武器", "戒指", "服装", "符文", "女神", "领主",
            "灵魂要塞", "活动日历","Buff的叠加方式","原画图鉴","游戏壁纸","CQ表情包","CQ联动史","CQ玩家用语集","勇士别名一览","过场画面","周年纪念册","游戏图标一览"};
    public final String[] areaType = {"全力战", "讨伐", "挑战", "外传", "试炼", "剧情", "竞技", "资源地牢"};

    public NormalUrlMessageProcessor() {
        this.command = "(^\\s*qr)|(^\\s*查询)";
    }

    @Override
    @SneakyThrows
    public Msg process(String message) {
        String parameter = getCommandParameters(message).get(0);
        if (StringUtils.isEmpty(parameter)) {
            return null;
        }
        for (String type : specialType) {
            if (type.startsWith(parameter) || type.endsWith(parameter)) {
                return Msg.builder().text(type + "\n" + WIKI_PATH + URLEncoder.encode(type, "utf-8"));
            }
        }
        for (String areaType : areaType) {
            if (areaType.startsWith(parameter) || areaType.endsWith(parameter)) {
                return Msg.builder().text(areaType + "\n" + WIKI_PATH + URLEncoder.encode(Constant.WIKI_AREA, "utf-8"));
            }
        }
        return null;
    }
}
