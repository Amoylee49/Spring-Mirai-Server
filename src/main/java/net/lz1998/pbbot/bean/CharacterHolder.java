package net.lz1998.pbbot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterHolder {
    private List<String> nickNames = new LinkedList<>();
    private Character character;

    public void addNickName(String nickName) {
        nickNames.add(nickName);
    }
}
