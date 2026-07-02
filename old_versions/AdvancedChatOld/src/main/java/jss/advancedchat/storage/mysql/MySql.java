package jss.advancedchat.storage.mysql;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.logger.Logger;
import jss.advancedchat.files.utils.Settings;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySql {

    private static final AdvancedChat plugin = AdvancedChat.get();

    public static void createTable() {
        try (Connection connection = plugin.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS `advancedchat_data` (" +
                            "`PLAYER_NAME` VARCHAR(30), " +
                            "`GROUP_NAME` VARCHAR(20), " +
                            "`CHAT_TYPE` VARCHAR(30), " +
                            "`COLOR_NAME` VARCHAR(16), " +
                            "`FIRST_GRADIENT` VARCHAR(20), " +
                            "`SECOND_GRADIENT` VARCHAR(20), " +
                            "`SPECIAL_CODES` VARCHAR(16), " +
                            "`RAINBOW` VARCHAR(30), " +
                            "`IS_MUTE` TINYINT(1), " +  // Cambiado a TINYINT(1)
                            "`LOWMODE` TINYINT(1), " +  // Cambiado a TINYINT(1)
                            "`IS_MSG` TINYINT(1), " +  // Cambiado a TINYINT(1)
                            "`IS_CHAT` TINYINT(1)" +   // Cambiado a TINYINT(1)
                            ")");
            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static boolean existsInPlayerDataBase(@NotNull Player player) {
        try (Connection connection = plugin.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM `advancedchat_data` WHERE PLAYER_NAME = ?"
            );
            statement.setString(1, player.getName());

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();  // Si hay un resultado, el jugador existe
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public static void createPlayerOld(Player player, String group) {
        setPlayer(player);
        setGroup(player, group);
        setChatType(player, "color");
        setColor(player, Settings.default_color);
        setFirstGradient(player, "FFFFFF");
        setSecondGradient(player, "FFFFFF");
        setSpecialCodes(player, "none");
        setRainbow(player, "rainbow_1");
        setMute(player, false);
        setLowMode(player, false);
        setMsg(player, true);
        setChat(player, true);
    }

    public static void createPlayer(Player player, String group) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO `advancedchat_data` " +
                                "(`PLAYER_NAME`, `GROUP_NAME`, `CHAT_TYPE`, `COLOR_NAME`, `FIRST_GRADIENT`, " +
                                "`SECOND_GRADIENT`, `SPECIAL_CODES`, `RAINBOW`, `IS_MUTE`, `LOWMODE`, `IS_MSG`, `IS_CHAT`) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, player.getName());
                statement.setString(2, group); // Asignar un valor por defecto para el grupo
                statement.setString(3, "color"); // Asignar un valor por defecto para el chatType
                statement.setString(4, Settings.default_color); // Ajusta con el valor adecuado para color
                statement.setString(5, "FFFFFF"); // Ajusta con el valor adecuado para first_gradient
                statement.setString(6, "FFFFFF"); // Ajusta con el valor adecuado para second_gradient
                statement.setString(7, "none"); // Ajusta con el valor adecuado para specialCodes
                statement.setString(8, "rainbow_1"); // Ajusta con el valor adecuado para rainbow
                statement.setBoolean(9, false); // Ajusta con el valor adecuado para mute
                statement.setBoolean(10, false); // Ajusta con el valor adecuado para lowMode
                statement.setBoolean(11, true); // Ajusta con el valor adecuado para msg
                statement.setBoolean(12, true); // Ajusta con el valor adecuado para chat
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    public static void setPlayer(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `advancedchat_data` VALUE (?)");
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
        }
    }

    public static void setChatType(Player player, String chatType) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) { // Cambié de '!' a '' para asegurarme de que el jugador ya esté en la DB
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE `advancedchat_data` SET CHAT_TYPE = ? WHERE PLAYER_NAME = ?"
                );
                statement.setString(1, chatType);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static void setGroup(Player player, String group) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET GROUP_NAME = ? WHERE (PLAYER_NAME=?)");
                statement.setString(2, group);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setColor(Player player, String color) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET COLOR_NAME = ? WHERE (PLAYER_NAME=?)");
                statement.setString(1, color);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setFirstGradient(Player player, String first_gradient) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET FIRST_GRADIENT = ? WHERE (PLAYER_NAME=?)");
                statement.setString(2, first_gradient);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setSecondGradient(Player player, String second_gradient) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET SECOND_GRADIENT = ? WHERE (PLAYER_NAME=?)");
                statement.setString(2, second_gradient);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setSpecialCodes(Player player, String group) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET SPECIAL_CODES = ? WHERE (PLAYER_NAME=?)");
                statement.setString(2, group);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setRainbow(Player player, String rainbow) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET RAINBOW = ? WHERE (PLAYER_NAME=?)");
                statement.setString(2, rainbow);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setMute(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_MUTE = ? WHERE (PLAYER_NAME=?)");
                statement.setBoolean(2, value);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setLowMode(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET LOWMODE = ? WHERE (PLAYER_NAME=?)");
                statement.setBoolean(2, value);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setMsg(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_MSG = ? WHERE (PLAYER_NAME=?)");
                statement.setBoolean(2, value);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static void setChat(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_CHAT = ? WHERE (PLAYER_NAME=?)");
                statement.setBoolean(2, value);
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
    }

    public static @Nullable String getChatType(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("CHAT_TYPE");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getGroup(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("GROUP_NAME");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getColor(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("COLOR_NAME");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getFirstGradient(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("FIRST_GRADIENT");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getSecondGradient(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("SECOND_GRADIENT");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getSpecialCodes(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("SPECIAL_CODES");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return null;
    }

    public static @Nullable String getRainbow(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("RAINBOW");
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return null;
    }

    public static boolean isMute(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_MUTE") == 1;
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return false;
    }

    public static boolean isLowMode(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("LOWMODE") == 1;
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return false;
    }

    public static boolean isMsg(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_MSG") == 1;
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return false;
    }

    public static boolean isChat(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_CHAT") == 1;
                }
            }
        } catch (SQLException e) {
            Logger.error(e.getMessage());
            throw  new RuntimeException(e);
        }
        return false;
    }

}
