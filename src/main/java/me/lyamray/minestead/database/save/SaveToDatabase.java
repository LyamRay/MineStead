package me.lyamray.minestead.database.save;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.database.Database;
import me.lyamray.minestead.animal.data.AnimalData;
import me.lyamray.minestead.player.data.PlayerData;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaveToDatabase {

    @Getter
    private static final SaveToDatabase instance = new SaveToDatabase();

    private final Database database = Database.getInstance().getDatabase();

    public void savePlayerData(UUID uuid) {
        PlayerData data = PlayerData.getInstance().getPlayerDataCache().get(uuid);
        if (data == null) {
            log.warn("Tried to save player data for {}, but no data found in cache.", uuid);
            return;
        }

        Map<String, Object> values = new HashMap<>();
        values.put("uuid", uuid.toString());
        values.put("money", data.getMoney());
        values.put("playtime", data.getPlaytime());
        values.put("tutorialFinished", data.isTutorialFinished());

        saveOrUpdate("players", uuid.toString(), values);
    }

    public void saveAnimalData(UUID uuid) {
        AnimalData data = AnimalData.getInstance().getAnimalDataCache().get(uuid);
        if (data == null) {
            log.warn("Tried to save animal data for {}, but no data found in cache.", uuid);
            return;
        }

        Map<String, Object> values = new HashMap<>();
        values.put("uuid", uuid.toString());
        values.put("owner_uuid", data.getOwnerUuid() != null ? data.getOwnerUuid().toString() : null);
        values.put("type", data.getType());
        values.put("age", data.getAge());
        values.put("health", data.getHealth());

        saveOrUpdate("animals", uuid.toString(), values);
    }

    private void saveOrUpdate(String table, Object whereValue, Map<String, Object> values) {
        try {
            if (database.checkIfExists(table, "uuid = ?", whereValue)) {
                database.set(table, values, "uuid = ?", whereValue);
            } else {
                database.add(table, values);
            }
        } catch (SQLException e) {
            log.warn("Failed to save to table {} for key {} - {}", table, whereValue, e.getMessage());
        }
    }

    public void saveAllPlayerData() {
        PlayerData.getInstance().getPlayerDataCache().values().forEach(data -> {
            savePlayerData(data.getUuid());
        });
    }

    public void saveAllAnimalData() {
        AnimalData.getInstance().getAnimalDataCache().values().forEach(data -> {
            saveAnimalData(data.getUuid());
        });
    }
}
