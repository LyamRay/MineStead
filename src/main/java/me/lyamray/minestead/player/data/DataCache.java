package me.lyamray.minestead.player.data;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class DataCache {
    @Getter
    private static final DataCache instance = new DataCache();

    private final Map<UUID, PlayerData> playerDataCache = new ConcurrentHashMap<>();
    private final Map<UUID, AnimalData> playerAnimalDataCache = new ConcurrentHashMap<>();

}
