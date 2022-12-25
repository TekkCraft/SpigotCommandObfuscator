package ch.tekk.savechat.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private FileConfiguration config;

    public ChatListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!this.config.getBoolean("playerConsoleChat")) {
            return;
        }

        Player player = event.getPlayer();
        String message = event.getMessage();

        Bukkit.broadcastMessage("<" + player.getDisplayName() + "> " + message);
        event.setCancelled(true);
    }
}
