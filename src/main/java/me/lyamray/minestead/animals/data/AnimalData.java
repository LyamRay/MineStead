package me.lyamray.minestead.animals.data;

import lombok.*;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalData {

    @Getter
    private static final AnimalData instance = new AnimalData();

    private final Map<UUID, AnimalData> AnimalDataCache = new ConcurrentHashMap<>();

    private UUID uuid;
    private UUID ownerUuid;
    private String type;
    private int age;
    private int health;

}