package de.unverwunderbar.legacy.legacygamemanager;

import de.unverwunderbar.legacy.legacygamemanager.commands.ReloadConfigCommand;
import de.unverwunderbar.legacy.legacygamemanager.commands.StartCommand;
import de.unverwunderbar.legacy.legacygamemanager.commands.StopCommand;
import de.unverwunderbar.legacy.legacygamemanager.listeners.WorldCreateListener;
import de.unverwunderbar.legacy.legacygamemanager.world.ServerWorld;
import org.bukkit.plugin.java.JavaPlugin;

public final class LegacyGameManager extends JavaPlugin {

    public static LegacyGameManager main;

    @Override
    public void onEnable() {
        main = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new WorldCreateListener(), this);

        getCommand("reload").setExecutor(new ReloadConfigCommand());
        getCommand("start").setExecutor(new StartCommand());
        getCommand("stop").setExecutor(new StopCommand());
    }

    @Override
    public void onDisable() {
        saveConfig();

        for(ServerWorld serverWorld : ServerWorld.getServerWorlds().values())
            serverWorld.delete();
    }
}
