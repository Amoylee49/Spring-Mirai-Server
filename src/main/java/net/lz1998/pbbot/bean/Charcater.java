package net.lz1998.pbbot.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
public class Charcater {

    private String name;
    private String title;
    private String content;
    private String imageUrl;

}
