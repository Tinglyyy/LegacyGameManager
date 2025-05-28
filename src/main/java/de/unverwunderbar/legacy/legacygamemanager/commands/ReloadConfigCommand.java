package de.unverwunderbar.legacy.legacygamemanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static de.unverwunderbar.legacy.legacygamemanager.LegacyGameManager.main;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("gamemanager.admin.reload"))
            main.reloadConfig();
        else
            sender.sendMessage("§cYou lack permission to do that!");

        return true;
    }
}
