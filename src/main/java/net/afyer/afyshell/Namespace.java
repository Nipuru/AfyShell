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

    public Location location(Player player) {
        return player.getLocation();
    }

    public Location location(Block block) {
        return block.getLocation();
    }
}
