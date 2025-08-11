package me.lyamray.minestead.database;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.lyamray.minestead.MineStead;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;

@Slf4j
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Database {

    @Getter
    private static final Database instance = new Database();

    private Database database;
    private Connection connection;

    public void setupDatabase() {
        MineStead mineStead = MineStead.getInstance();
        Path dataFolder = mineStead.getDataFolder().toPath();

        try {
            Files.createDirectories(dataFolder);
            database = new Database(dataFolder.resolve("MineStead.db").toString());
            log.info("Connected successfully to the database!");
        } catch (IOException e) {
            handleFatal("Failed to create the database folder", e, mineStead);
        } catch (SQLException e) {
            handleFatal("Failed to connect to the database", e, mineStead);
        }
    }

    private void handleFatal(String message, Exception e, MineStead mineStead) {
        log.warn(message + ": " + e.getMessage());
        Bukkit.getPluginManager().disablePlugin(mineStead);
        throw new RuntimeException(message, e);
    }

    private Database(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + path);
        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                        CREATE TABLE IF NOT EXISTS players (
                            uuid TEXT PRIMARY KEY,
                            money INTEGER DEFAULT 0,
                            playtime INTEGER DEFAULT 0,
                            tutorialFinished BOOLEAN
                        );
                    """);

            statement.execute("""
                    CREATE TABLE IF NOT EXISTS animals (
                        uuid TEXT PRIMARY KEY,
                        owner_uuid TEXT,
                        type TEXT,
                        age INTEGER,
                        health INTEGER
                    );
                    """);
        }
    }

    //add, get, set, remove, check if (player bestaat in een tabel ergens)

    public void add(String table, Map<String, Object> values) throws SQLException {
        String columns = String.join(", ", values.keySet());
        String placeholders = String.join(", ", Collections.nCopies(values.size(), "?"));
        executeUpdate("INSERT INTO " + table + " (" + columns + ") VALUES (" + placeholders + ")", values.values().toArray());
    }

    public List<Map<String, Object>> get(String table, String whereClause, Object... params) throws SQLException {
        return executeQuery(buildSelectSql(table, whereClause), params);
    }

    public void set(String table, Map<String, Object> updates, String whereClause, Object... params) throws SQLException {
        List<Object> allParams = new ArrayList<>(updates.values());
        allParams.addAll(Arrays.asList(params));
        executeUpdate(buildUpdateSql(table, updates.keySet(), whereClause), allParams.toArray());
    }

    public void remove(String table, String whereClause, Object... params) throws SQLException {
        executeUpdate(buildDeleteSql(table, whereClause), params);
    }

    public boolean checkIfExists(String table, String whereClause, Object... params) throws SQLException {
        String sql = buildSelectSql(table, whereClause) + " LIMIT 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            bindParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    //helpers voor bovenstaande functions

    private String buildSelectSql(String table, String where) {
        return "SELECT * FROM " + table + (where != null && !where.isEmpty() ? " WHERE " + where : "");
    }

    private String buildUpdateSql(String table, Set<String> columns, String where) {
        String setClause = String.join(", ", columns.stream().map(k -> k + " = ?").toList());
        return "UPDATE " + table + " SET " + setClause + (where != null && !where.isEmpty() ? " WHERE " + where : "");
    }

    private String buildDeleteSql(String table, String where) {
        return "DELETE FROM " + table + (where != null && !where.isEmpty() ? " WHERE " + where : "");
    }

    private void executeUpdate(String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            bindParams(ps, params);
            ps.executeUpdate();
        }
    }

    private List<Map<String, Object>> executeQuery(String sql, Object... params) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            bindParams(ps, params);
            try (ResultSet rs = ps.executeQuery()) {
                return mapResultSet(rs);
            }
        }
    }

    private void bindParams(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    private List<Map<String, Object>> mapResultSet(ResultSet rs) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                row.put(meta.getColumnName(i), rs.getObject(i));
            }
            result.add(row);
        }
        return result;
    }
}
