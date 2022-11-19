package net.lz1998.pbbot.controller;

import net.lz1998.pbbot.bean.CharacterHolder;
import net.lz1998.pbbot.method.CharacterCaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CharacterController {

    @Autowired
    private CharacterCaches characterCaches;

    @RequestMapping(path = "/getAllCharacters")
    public List<CharacterHolder> getAllCharacters() {
        return characterCaches.getAllCharacters();
    }

    @RequestMapping(path = "/refresh")
    public boolean refreshAllCharacters() {
        return characterCaches.cacheAllCharactersFromWeb();
    }
}
