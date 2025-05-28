package de.unverwunderbar.legacy.legacygamemanager.world;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.UUID;

import static de.unverwunderbar.legacy.legacygamemanager.LegacyGameManager.main;

public abstract class ServerWorld {
    @Getter
    private static HashMap<World, ServerWorld> serverWorlds = new HashMap<>();

    @Getter
    private World ingameWorld;

    @Getter
    private final World template;

    protected ServerWorld(World template) {
        this.template = template;

        if(serverWorlds.containsKey(template))
            serverWorlds.replace(template, this);
        else
            serverWorlds.put(template, this);
    }

    public boolean create() {
        return create("");
    }

    protected boolean create(String extra) {
        Path srcDir = this.template.getWorldFolder().toPath();
        Path destDir = this.template.getWorldFolder().getParentFile().toPath();
        try {
            Files.copy(srcDir, destDir);
        } catch (IOException e) {
            main.getLogger().severe(e.getMessage());
            return false;
        }

        File file = new File(destDir + "/uid.dat");
        file.delete();

        this.ingameWorld = Bukkit.getServer().createWorld(new WorldCreator(extra + UUID.randomUUID()));

        return true;
    }



    public boolean delete() {
        Bukkit.unloadWorld(this.ingameWorld, false);

        boolean res = deleteWorldFile(this.ingameWorld.getWorldFolder());

        if(res)
            main.getLogger().info("Deleted world " + this.ingameWorld.getName() + " successfully");
        else
            main.getLogger().severe("Couldn't delete world " + this.ingameWorld.getName());

        return res;
    }


    protected static boolean deleteWorldFile(File path) {
        if(path.exists()) {
            File files[] = path.listFiles();
            for(int i=0; i<files.length; i++)
                if(files[i].isDirectory())
                    deleteWorldFile(files[i]);
                 else
                    files[i].delete();
        }
        return(path.delete());
    }

}
