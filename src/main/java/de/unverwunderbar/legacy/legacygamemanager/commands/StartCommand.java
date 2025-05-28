package de.unverwunderbar.legacy.legacygamemanager.commands;

import de.unverwunderbar.legacy.legacygamemanager.world.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /**
         * Admin needed to start external games
         */

        GameWorld world;

        if(args.length == 1 && sender.hasPermission("gamemanager.admin.start")) {
            world = GameWorld.fromWorld(Bukkit.getWorld(args[0]));

            if(world == null) {
                sender.sendMessage("§cWorld §l" + world.getIngameWorld().getName() + "§r§c doesn't exist");
                return true;
            }

            world.start();

        }
        else if(sender instanceof Player player && sender.hasPermission("gamemanager.start")) {
            world = GameWorld.fromWorld(player.getWorld());

            if(world == null) {
                player.sendMessage("§cNot currently in a game world!");
                return true;
            }

            world.start();
        }

        else
            sender.sendMessage("§cYou lack permission to do that!");

        return true;
    }
}
