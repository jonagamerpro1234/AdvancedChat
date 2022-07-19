package jss.advancedchat.storage.mysql;

import jss.advancedchat.AdvancedChat;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySql {

    private static AdvancedChat plugin = AdvancedChat.get();

    public static void createTable(){
        try(Connection connection = plugin.getConnection()){
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `advancedchat_data` (`PLAYER_NAME` VARCHAR(100), `GROUP_NAME` VARCHAR(20), `CHAT_TYPE` VARCHAR(30)," +
                    " `UUID` VARCHAR(200), `COLOR_NAME` VARCHAR(16), `FIRST_GRADIENT` VARCHAR(20), `SECOND_GRADIENT` VARCHAR(20), `SPECIAL_CODES` VARCHAR(16), `RAINBOW` VARCHAR(30)" +
                    " `IS_MUTE` BOOLEAN), `LOWMODE` BOOLEAN, `IS_MSG` BOOLEAN, `IS_CHAT` BOOLEAN)))");
            statement.executeUpdate();
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createPlayer(){

    }


    public static void setChatType(Player player, String chattype){

    }

    public static void setGroup(){

    }
}
