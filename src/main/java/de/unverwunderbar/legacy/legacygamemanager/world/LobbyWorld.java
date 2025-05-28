package de.unverwunderbar.legacy.legacygamemanager.world;

import lombok.Getter;
import org.bukkit.World;

import java.util.HashMap;

public class LobbyWorld extends ServerWorld{
    @Getter
    private static HashMap<World, LobbyWorld> lobbyWorlds = new HashMap<>();

    @Getter
    private GameWorld gameWorld;

    public LobbyWorld(World inGameWorld, GameWorld gameWorld) {
        super(inGameWorld);

        this.gameWorld = gameWorld;

        if(lobbyWorlds.containsKey(inGameWorld))
            lobbyWorlds.replace(inGameWorld, this);
        else
            lobbyWorlds.put(inGameWorld, this);
    }

    @Override
    public boolean create() {
        return create("LobbyWorld");
    }


}
