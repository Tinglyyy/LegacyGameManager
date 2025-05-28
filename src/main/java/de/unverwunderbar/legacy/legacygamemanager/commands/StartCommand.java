package de.unverwunderbar.legacy.legacygamemanager.commands;

import de.unverwunderbar.legacy.legacygamemanager.world.GameWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.hasPermission("gamemanager.start")) {

            if(args.length == 1) {
                World world = Bukkit.getWorld(args[0]);

                if(world == null) {
                    sender.sendMessage("§cWorld §l" + world.getName() + "§r§c doesn't exist");
                    return true;
                }

                GameWorld.fromWorld(world).start();

            }
            else if(sender instanceof Player player)
                GameWorld.fromWorld(player.getWorld()).start();
        }
        else
            sender.sendMessage("§cYou lack permission to do that!");

        return true;
    }
}
