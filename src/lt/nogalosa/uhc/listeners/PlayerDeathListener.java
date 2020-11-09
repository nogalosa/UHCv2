package lt.nogalosa.uhc.listeners;

import lt.nogalosa.uhc.Main;
import lt.nogalosa.uhc.Period;
import lt.nogalosa.uhc.controllers.GameController;
import lt.nogalosa.uhc.controllers.PlayerController;
import lt.nogalosa.uhc.models.UPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void playerDeathListener (PlayerDeathEvent event) {
        event.setDeathMessage(null);
        if(GameController.getInstance().getPeriod() != Period.PREGAME && GameController.getInstance().getPeriod() != Period.STARTING) {
            UPlayer player = PlayerController.getInstance().findPlayer(event.getEntity());
            player.setDead(true);
            event.getEntity().kickPlayer("§eJūs buvote nužudytas!\n\n§rNužudė: " + player.getKillerName() + "\n\n§cAčiū už žaidimą!\n§7Turnyro tiesioginę transliaciją galite stebėti mūsų kanaluose.");
        } else {
            event.getDrops().clear();
            new BukkitRunnable() { // auto respawn
                public void run() {
                    ((CraftPlayer) event.getEntity()).getHandle().playerConnection
                            .a(new PacketPlayInClientCommand(PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                }
            }.runTaskLater(Main.getInstance(), 2);
        }
    }
}
