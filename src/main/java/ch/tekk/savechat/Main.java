package ch.tekk.savechat;

import ch.tekk.savechat.listeners.PlayerCommandSendListener;
import ch.tekk.savechat.listeners.TabCompleteListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ch.tekk.savechat.listeners.ChatListener;

public class Main extends JavaPlugin {
    private FileConfiguration config;

    @Override
    public void onEnable() {
        this.ensureConfig();

        this.getServer().getPluginManager().registerEvents(new ChatListener(config), this);
        this.getServer().getPluginManager().registerEvents(new TabCompleteListener(config), this);
        this.getServer().getPluginManager().registerEvents(new PlayerCommandSendListener(config), this);
    }

    @Override
    public void onDisable() {
    }

    /**
     * Ensures the existence of a config file.
     */
    private void ensureConfig() {
        this.saveDefaultConfig();
        this.reloadConfig();
        this.config = this.getConfig();
    }
}