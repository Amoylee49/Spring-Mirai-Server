package net.lz1998.pbbot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    private String name;
    private String title;
    private String content;
    private String imageUrl;
    private String pageUrl;
}
