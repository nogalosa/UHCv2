package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.controllers.PlayerController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void playerJoinListener(PlayerJoinEvent ev) {
        PlayerController.getInstance().addPlayer(ev.getPlayer());
    }
}
