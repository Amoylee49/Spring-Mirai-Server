package net.lz1998.pbbot.controller;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bean.CharacterHolder;
import net.lz1998.pbbot.method.CharacterCaches;
import net.lz1998.pbbot.method.CharacterCachesStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.*;

@RestController
@Slf4j
public class CharacterController {

    private final ThreadPoolExecutor refreshPool =
            new ThreadPoolExecutor(
                    1,1,
                    0L, TimeUnit.MILLISECONDS,
                    new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy()
            );

    @Autowired
    private CharacterCaches characterCaches;

    @Autowired
    private CharacterCachesStatus status;

    @RequestMapping(path = "/getAllCharacters")
    public List<CharacterHolder> getAllCharacters() {
        return characterCaches.getAllCharacters();
    }

    @RequestMapping(path = "/refresh")
    public String refreshAllCharacters() {
        try {
            refreshPool.execute(() -> {
                try {
                    characterCaches.cacheAllCharactersFromWeb();
                } catch (Exception e) {
                    log.error("刷新人物缓存出现异常！", e);
                }
            });
        } catch (RejectedExecutionException e) {
            return String.format("总进度：%d/%d，加载具体勇士进度：%d/%d",
                    status.getInTotalProgress(),
                    status.getTotalProgress(),
                    status.getInSubProgress(),
                    status.getSubProgress()
            );
        }
        return "正在刷新中";
    }
}
