package net.lz1998.pbbot.controller;

import net.lz1998.pbbot.strategy.MessageProcessor;
import net.lz1998.pbbot.utils.Msg;
import onebot.OnebotBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private List<MessageProcessor> messageProcessors;

    @RequestMapping(path = "/cmd")
    public String testCommand(@RequestParam String cmd) {
        for (MessageProcessor messageProcessor : messageProcessors) {
            if (messageProcessor.isMatch(cmd)) {
                Msg msg = messageProcessor.process(cmd);
                if (msg != null) {
                    StringBuilder result = new StringBuilder();
                    List<OnebotBase.Message> messages = msg.build();
                    for (OnebotBase.Message message : messages) {
                        result.append(message.toString());
                    }
                    return result.toString();
                }
            }
        }
        return "未查询到结果！";
    }
}
