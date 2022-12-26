package ch.tekk.savechat.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TabCompleteListener implements Listener {

    private FileConfiguration config;

    public TabCompleteListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onTabComplete(TabCompleteEvent event) {
        if (event.getSender().hasPermission("save-chat.bypass") || event.getSender().isOp()) {
            return;
        }

        if (!this.isEnabled()) {
            return;
        }

        String buffer = event.getBuffer();
        List<String> completions = event.getCompletions();
        List<String> allowed = this.getAllowedCommands();

        List<Integer> deleteIndexes = new ArrayList<>();

        int count = 0;
        for (String cmp : completions) {
            String command = buffer + cmp;

            if (!allowed.contains(command)) {
                deleteIndexes.add(count);
            }

            count++;
        }

        deleteIndexes.sort(Comparator.reverseOrder());
        for (int index : deleteIndexes) {
            completions.remove(index);
        }

        event.setCompletions(completions);
    }

    private List<String> getAllowedCommands() {
        List<String> allowedCommands = this.config.getStringList("allowedCommands");
        List<String> preSlashedCommands = new ArrayList<String>();

        for (String command : allowedCommands) {
            preSlashedCommands.add("/" + command);
        }

        return preSlashedCommands;
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
