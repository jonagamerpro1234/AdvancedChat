package jss.advancedchat.storage.utils;

import jss.advancedchat.storage.PlayerData;

public interface IStorage {

    public void savePlayerData(PlayerData playerData);

    public PlayerData loadPlayerData(String playerName);

}
