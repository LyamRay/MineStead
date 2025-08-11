package me.lyamray.minestead.player.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {

    private UUID uuid;
    private int money;
    private int playtime;

}
