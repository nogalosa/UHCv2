package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.controllers.PlayerController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void playerQuitListener(PlayerQuitEvent ev) {
        PlayerController.getInstance().removePlayer(ev.getPlayer());
    }
}
