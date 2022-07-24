package jss.advancedchat.storage.mysql;

import jss.advancedchat.AdvancedChat;
import jss.advancedchat.utils.Settings;
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
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `advancedchat_data` (`PLAYER_NAME` VARCHAR(30), `GROUP_NAME` VARCHAR(20), `CHAT_TYPE` VARCHAR(30)," +
                    " `COLOR_NAME` VARCHAR(16), `FIRST_GRADIENT` VARCHAR(20), `SECOND_GRADIENT` VARCHAR(20)," +
                    " `SPECIAL_CODES` VARCHAR(16), `RAINBOW` VARCHAR(30)" +
                    " `IS_MUTE` BOOLEAN, `LOWMODE` BOOLEAN, `IS_MSG` BOOLEAN, `IS_CHAT` BOOLEAN)");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean existsInPlayerDataBase(@NotNull Player player) {
        try (Connection connection = plugin.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
            statement.setString(1, player.getName());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(Player player, String group) {
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

    public static void setPlayer(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO `advancedchat_data` VALUE (?)");
                statement.setString(1, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setChatType(Player player, String chattype) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET CHAT_TYPE WHERE (PLAYER_NAME=?)");
                statement.setString(1, chattype);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setGroup(Player player, String group) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET GROUP_NAME WHERE (PLAYER_NAME=?)");
                statement.setString(1, group);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setColor(Player player, String color) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET COLOR_NAME WHERE (PLAYER_NAME=?)");
                statement.setString(1, color);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setFirstGradient(Player player, String first_gradient) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET FIRST_GRADIENT WHERE (PLAYER_NAME=?)");
                statement.setString(1, first_gradient);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setSecondGradient(Player player, String second_gradient) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET SECOND_GRADIENT WHERE (PLAYER_NAME=?)");
                statement.setString(1, second_gradient);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setSpecialCodes(Player player, String group) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET SPECIAL_CODES WHERE (PLAYER_NAME=?)");
                statement.setString(1, group);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setRainbow(Player player, String rainbow) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET RAINBOW WHERE (PLAYER_NAME=?)");
                statement.setString(1, rainbow);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMute(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_MUTE WHERE (PLAYER_NAME=?)");
                statement.setBoolean(1, value);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setLowMode(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET LOWMODE WHERE (PLAYER_NAME=?)");
                statement.setBoolean(1, value);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setMsg(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_MSG WHERE (PLAYER_NAME=?)");
                statement.setBoolean(1, value);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setChat(Player player, boolean value) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("UPDATE `advancedchat_data` SET IS_CHAT WHERE (PLAYER_NAME=?)");
                statement.setBoolean(1, value);
                statement.setString(2, player.getName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static @Nullable String getChatType(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("CHAT_TYPE");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static @Nullable String getGroup(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("GROUP_NAME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static @Nullable String getColor(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("COLOR_NAME");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static @Nullable String getFirstGradient(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("FIRST_GRADIENT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSecondGradient(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("SECOND_GRADIENT");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSpecialCodes(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("SPECIAL_CODES");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRainbow(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getString("RAINBOW");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isMute(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_MUTE") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isLowMode(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("LOWMODE") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isMsg(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_MSG") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isChat(Player player) {
        try (Connection connection = plugin.getConnection()) {
            if (!existsInPlayerDataBase(player)) {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM `advancedchat_data` WHERE (PLAYER_NAME=?)");
                statement.setString(1, player.getName());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    return resultSet.getInt("IS_CHAT") == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
