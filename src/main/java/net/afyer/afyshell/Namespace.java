package net.afyer.afyshell;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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

    public Class<?> clazz(String packageName) {
        try {
            return Class.forName(packageName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
