package lt.nogalosa.uhc.controllers;

import lt.nogalosa.uhc.models.UPlayer;
import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerController {

    CopyOnWriteArrayList<UPlayer> players = new CopyOnWriteArrayList<>();

    private static PlayerController instance;

    public static PlayerController getInstance() {
        if(instance == null)
            instance = new PlayerController();
        return instance;
    }

    public void addPlayer(Player player) {
        UPlayer uPlayer = new UPlayer(player.getUniqueId());
        players.add(uPlayer);
    }

    public UPlayer findPlayer(Player player) {
        return findPlayer(player.getUniqueId());
    }

    public UPlayer findPlayer(UUID uuid) {
        return players.stream().filter(player -> uuid.equals(player.getUuid())).findFirst().orElse(null);
    }

    public void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }

    public void removePlayer(UUID uuid) {
        UPlayer uPlayer = findPlayer(uuid);
        if(uPlayer != null){
            players.remove(uPlayer);
        } else {
            Msg.console("uPlayer of UUID " + uuid + " not found. It was not removed from PlayerController.");
        }
    }

    public CopyOnWriteArrayList<UPlayer> getPlayers() {
        return players;
    }
}
