package lt.nogalosa.uhc.models;

import lt.nogalosa.uhc.Config;
import lt.nogalosa.uhc.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class UPlayer {

    UUID uuid;

    public UPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public Location teleportRandom() {
        Player player = this.getPlayer();
        int x = Main.getRandom().nextInt(Config.WORLD_SIZE_X) - Config.WORLD_SIZE_X / 2;
        int z = Main.getRandom().nextInt(Config.WORLD_SIZE_Z) - Config.WORLD_SIZE_Z / 2;
        World world = player.getWorld();
        Location to = world.getHighestBlockAt(x,z).getLocation().clone().add(0,1,0);
        player.teleport(to);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS,Integer.MAX_VALUE,1,true,false));
        return to;
    }

    public void clearPlayer() {
        Player player = this.getPlayer();
        for(PotionEffect e : player.getActivePotionEffects()){
            player.removePotionEffect(e.getType());
        }
        player.getInventory().clear();
        player.setFoodLevel(20);
        player.setHealth(20);
    }
}
