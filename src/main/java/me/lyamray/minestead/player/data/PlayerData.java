package me.lyamray.minestead.player.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {

    @Getter
    private static final PlayerData instance = new PlayerData();

    private UUID uuid;
    private int money;
    private int playtime;
    private boolean tutorialFinished;

}
