package net.lz1998.pbbot.strategy;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.constant.Constant;
import net.lz1998.pbbot.util.StringUtils;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotBase;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import static net.lz1998.pbbot.constant.Constant.WIKI_PATH;

@Component
@Slf4j
@Order(1)
public class NormalUrlMessageProcessor extends MessageProcessor {
    public final String[] specialType = {"勇士登场期数参考表", "特殊技能", "精淬武器", "戒指", "服装", "符文", "女神", "领主",
            "灵魂要塞", "活动日历"};
    public final String[] areaType = {"讨伐", "挑战", "外传", "试炼", "剧情", "竞技", "资源地牢"};

    public NormalUrlMessageProcessor() {
        this.command = "(^\\s*qr)|(^\\s*查询)";
    }

    @Override
    public List<OnebotBase.Message> process(String message) {
        String parameter = getCommandParameters(message).get(0);
        if (StringUtils.isEmpty(parameter)) {
            return null;
        }
        for (String type : specialType) {
            if (type.startsWith(parameter) || type.endsWith(parameter)) {
                return Msg.builder().image(Constant.WIKI_LOGO_PATH)
                        .share(WIKI_PATH + type,
                                type+Constant.WIKI_TITLE_TAIL, Constant.WIKI_NORMAL_CONTENT, Constant.WIKI_LOGO_PATH
                        ).build();
            }
        }
        for (String areaType : areaType){
            if (areaType.startsWith(parameter) || areaType.endsWith(parameter)){
                try {
                    return Msg.builder().text(WIKI_PATH + URLEncoder.encode(Constant.WIKI_AREA, "utf-8")).build();
                } catch (UnsupportedEncodingException e) {
                    throw  new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
