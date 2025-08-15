package me.lyamray.minestead.animal.data;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public class AnimalDataHandler {

    @Getter
    private static final AnimalDataHandler instance = new AnimalDataHandler();


    private final Map<UUID, AnimalData> animalDataCache = new ConcurrentHashMap<>();

    public void setData(AnimalData data) {
        animalDataCache.put(data.getUuid(), data);
    }

    public AnimalData getData(UUID uuid) {
        return animalDataCache.computeIfAbsent(uuid,
                id -> new AnimalData(id, null, "Unknown", 0, 0));
    }

    public void addData(AnimalData data) {
        animalDataCache.putIfAbsent(data.getUuid(), data);
    }

    public void removeData(UUID uuid) {
        animalDataCache.remove(uuid);
    }

    public boolean hasData(UUID uuid) {
        return animalDataCache.containsKey(uuid);
    }
}
