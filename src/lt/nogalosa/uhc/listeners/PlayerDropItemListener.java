package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerDropItemListener implements Listener {

    @EventHandler
    public void playerDropItemListener(PlayerDropItemEvent event) {
        if(GameController.getInstance().getPeriod() == Period.PREGAME) {
            if(!event.getPlayer().isOp()){
                event.setCancelled(true);
            }
        }
    }
}
