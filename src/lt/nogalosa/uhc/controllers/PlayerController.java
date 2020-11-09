package lt.nogalosa.uhc.controllers;

import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.models.UPlayer;
import lt.nogalosa.uhc.utils.Msg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerController {

    CopyOnWriteArrayList<UPlayer> players = new CopyOnWriteArrayList<>();
    CopyOnWriteArrayList<UPlayer> disconnectedPlayers = new CopyOnWriteArrayList<>();

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

    public boolean hasPlayer(Player player){
        return hasPlayer(player.getUniqueId());
    }

    public boolean hasPlayer(UUID uuid) {
        return findPlayer(uuid) != null;
    }

    public void removePlayer(Player player) {
        removePlayer(player.getUniqueId());
    }

    public void removePlayer(UUID uuid) {
        UPlayer uPlayer = findPlayer(uuid);
        if(uPlayer != null){
            uPlayer.setDisconnected(true);
        } else {
            Msg.console("uPlayer of UUID " + uuid + " not found. It was not removed from PlayerController.");
        }
    }

    public CopyOnWriteArrayList<UPlayer> getPlayers() {
        return players;
    }

    public void playerDeath(Player pl, Entity damager) {
        if(GameController.getInstance().getPeriod() == Period.PREGAME || GameController.getInstance().getPeriod() == Period.STARTING)
            return;

        UPlayer player = findPlayer(pl);
        player.setDead(true);
        if(damager != null){
            String killerName = "Nežinia kas";
            if(damager instanceof Player){
                killerName = ((Player)damager).getName();
            } else {
                killerName = damager.getName();
            }
            player.setKillerName(killerName);
            Msg.sa("§7" + killerName + " nužudė " + pl.getName() + ".");
        }else {
            Msg.sa(pl.getName() + " žuvo");
        }
    }
}
