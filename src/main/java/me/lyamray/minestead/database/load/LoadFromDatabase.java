package me.lyamray.minestead.database.load;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.animal.data.AnimalData;
import me.lyamray.minestead.player.data.PlayerData;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoadFromDatabase {

    @Getter
    private static final LoadFromDatabase instance = new LoadFromDatabase();

    private final Database database = Database.getInstance().getDatabase();

    public PlayerData loadPlayerData(UUID uuid) {
        try {
            List<Map<String, Object>> result = database.get("players", "uuid = ?", uuid.toString());

            if (result.isEmpty()) {
                return new PlayerData(uuid, 0, 0);
            }

            Map<String, Object> row = result.getFirst();
            int money = ((Number) row.get("money")).intValue();
            int playtime = ((Number) row.get("playtime")).intValue();

            return new PlayerData(uuid, money, playtime);
        } catch (SQLException e) {
            log.warn("Could not load player data for UUID: {} - {}", uuid, e.getMessage());
            return new PlayerData(uuid, 0, 0);
        }
    }

    public void loadAnimalData() {
        try {
            List<Map<String, Object>> results = database.get("animals", null);

            for (Map<String, Object> row : results) {
                UUID uuid = UUID.fromString((String) row.get("uuid"));
                String ownerUuidStr = (String) row.get("owner_uuid");
                UUID ownerUuid = ownerUuidStr != null ? UUID.fromString(ownerUuidStr) : null;
                String type = (String) row.get("type");
                int age = ((Number) row.get("age")).intValue();
                int health = ((Number) row.get("health")).intValue();

                AnimalData animalData = new AnimalData(uuid, ownerUuid, type, age, health);
                AnimalData.getInstance().getAnimalDataCache().put(uuid, animalData);
            }
        } catch (SQLException e) {
            log.warn("Could not load the animals from the database! ", e);
        }
    }
}