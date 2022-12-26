package ch.tekk.commandobfuscator.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.List;

public class PlayerCommandSendListener implements Listener {

    private final FileConfiguration config;

    public PlayerCommandSendListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandSendEvent event) {
        if (event.getPlayer().hasPermission("command-obfuscator.bypass") || event.getPlayer().isOp()) {
            return;
        }

        if (!this.isEnabled()) {
            return;
        }

        List<String> allowedCommands = this.config.getStringList("allowedCommands");

        event.getCommands().retainAll(allowedCommands);
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
