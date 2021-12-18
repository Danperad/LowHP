package com.danperad.lowhplib.db;

import com.danperad.lowhplib.PlayerLow;
import org.sqlite.JDBC;

import java.sql.*;

public class DAO {
    private static final String CON_STR = "jdbc:sqlite:.\\world\\lowhp.sqlite";

    private static Connection connection;

    public static PlayerLow getPlayer(String UUID) {
        PlayerLow p = new PlayerLow();
        String sql = "SELECT UUID, playername, lifes, adv FROM Players WHERE UUID = '" + UUID + "'";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            p.setUUID(rs.getString("UUID"));
            p.setName(rs.getString("playername"));
            p.setLifes(rs.getInt("lifes"));
            p.setAdv(rs.getInt("adv"));
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertPlayer(PlayerLow player) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Players(`UUID`, `playername`, `lifes`, `adv`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, player.getUUID());
            statement.setObject(2, player.getName());
            statement.setObject(3, player.getLifes());
            statement.setObject(4, player.getAdv());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlayer(PlayerLow player) {
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE Players SET lifes = ?, adv = ? WHERE UUID = '" + player.getUUID() + "'")) {
            statement.setObject(1, player.getLifes());
            statement.setObject(2, player.getAdv());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleatePlayer(PlayerLow player) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM Players WHERE UUID = '" + player.getUUID() + "'")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void init() {
        try {
            createDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDB() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        connection = DriverManager.getConnection(CON_STR);
        String sql = "CREATE TABLE IF NOT EXISTS Players (\n"
                + "	UUID varchar PRIMARY KEY,\n"
                + "	playername varchar NOT NULL,\n"
                + "	lifes integer NOT NULL,\n"
                + "	adv integer NOT NULL\n"
                + ");";
        Statement stmt = connection.createStatement();
        stmt.execute(sql);
    }
}
