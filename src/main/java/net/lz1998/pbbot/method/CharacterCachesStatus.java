package net.lz1998.pbbot.method;

import lombok.Data;
import org.springframework.stereotype.Component;

import static net.lz1998.pbbot.constant.Constant.CHARACTER_TYPE;

@Component
@Data
public class CharacterCachesStatus {
    private Integer totalProgress = CHARACTER_TYPE.length;
    private Integer inTotalProgress = CHARACTER_TYPE.length;
    private Integer subProgress = 0;
    private Integer inSubProgress = 0;

    public void clearStatus() {
        inTotalProgress = 0;
        subProgress = 0;
        inSubProgress = 0;
    }
}
