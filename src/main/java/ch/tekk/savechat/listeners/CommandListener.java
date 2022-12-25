package ch.tekk.savechat.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandListener implements Listener {

    private FileConfiguration config;

    public CommandListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!this.isEnabled()) {
            System.out.println("Command whitelist is disabled");

            return;
        }

        List<String> allowedCommands = this.config.getStringList("allowedCommands");

        String message = event.getMessage();

        if (!allowedCommands.contains(message)) {
            Player player = event.getPlayer();

            player.sendMessage(ChatColor.RED + "This command does not exist.");

            event.setCancelled(true);
        }
    }

    private boolean isEnabled() {
        if (!this.config.isBoolean("commandWhiteList")) {
            return false;
        }

        if (!this.config.getBoolean("commandWhiteList")) {
            return false;
        }

        if (!this.config.isList("allowedCommands")) {
            return false;
        }
        return true;
    }
}
