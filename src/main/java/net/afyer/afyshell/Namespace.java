package net.afyer.afyshell;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

/**
 * @author Nipuru
 * @since 2022/3/17 20:52
 */
public class Namespace {

    public Player player(String name) {
        return Bukkit.getPlayer(name);
    }

    public World world(String world) {
        return Bukkit.getWorld(world);
    }

    public Class<?> cls(String packageName) {
        try {
            return Class.forName(packageName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Entity target(Player player) {
        RayTraceResult rt = player.getWorld().rayTraceEntities(player.getLocation(), player.getLocation().getDirection(), 5);
        return rt != null ? rt.getHitEntity() : null;
    }

}
