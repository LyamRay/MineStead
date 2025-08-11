package me.lyamray.minestead.player.data;

import lombok.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {

    @Getter
    private static final PlayerData instance = new PlayerData();

    private final Map<UUID, PlayerData> playerDataCache = new ConcurrentHashMap<>();

    private UUID uuid;
    private int money;
    private int playtime;
    private boolean tutorialFinished;

}
