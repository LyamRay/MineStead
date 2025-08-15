package me.lyamray.minestead.player.data;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DataHandler {

    @Getter
    private static final DataHandler instance = new DataHandler();

    private final Map<UUID, PlayerData> playerDataCache = new ConcurrentHashMap<>();

    public void setData() {

    }

    public void getData() {

    }

    public void addData() {

    }

    public void removeData() {

    }
}
