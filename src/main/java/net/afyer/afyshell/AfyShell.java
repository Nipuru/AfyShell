package net.afyer.afyshell;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

/**
 * @author Nipuru
 * @since 2022/3/17 11:48
 */
public class AfyShell extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(getCommand("afyshell")).setExecutor(new Command());
    }
}
