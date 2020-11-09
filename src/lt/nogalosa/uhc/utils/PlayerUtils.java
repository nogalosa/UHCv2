package lt.nogalosa.uhc.utils;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerUtils {

    public static Entity getKiller(Entity ent) {
        EntityDamageEvent event = ent.getLastDamageCause();
        return getKiller(event);
    }

    public static Entity getKiller(EntityDamageEvent event) {
        if(event != null && !event.isCancelled() && (event instanceof EntityDamageByEntityEvent)) {
            Entity damager = ((EntityDamageByEntityEvent) event).getDamager();
            if(damager instanceof Projectile) {
                Object shooter = ((Projectile) damager).getShooter();
                if (shooter != null && (shooter instanceof Entity)) return (Entity) shooter;
            }

            return damager;
        }

        return null;
    }
}
