package net.lz1998.pbbot.runner;

import net.lz1998.pbbot.method.CharacterCaches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheCharacterRunner implements ApplicationRunner {
    @Autowired
    private CharacterCaches characterCaches;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        characterCaches.cacheAllCharactersFromJson();
    }
}
