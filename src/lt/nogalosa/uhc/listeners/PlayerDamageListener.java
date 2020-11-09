package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.controllers.GameController;
import lt.nogalosa.uhc.controllers.PlayerController;
import lt.nogalosa.uhc.utils.PlayerUtils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    @EventHandler
    public void playerDamageListener(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player))
            return;

        Player pl = (Player) event.getEntity();

        if(pl.getHealth() - event.getFinalDamage() <= 0) {
            Entity damager = PlayerUtils.getKiller(event.getEntity());
            PlayerController.getInstance().playerDeath(pl, damager);
        }
    }
}
