package me.lyamray.minestead.player.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {

    private UUID uuid;
    private int money;
    private int playtime;

}
