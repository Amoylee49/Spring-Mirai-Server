package net.lz1998.pbbot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckAliveController {

    @RequestMapping(path = "/checkAlive")
    public boolean checkAlive() {
        return true;
    }
}
