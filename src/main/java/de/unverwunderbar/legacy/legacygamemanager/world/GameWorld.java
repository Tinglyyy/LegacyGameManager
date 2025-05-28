package de.unverwunderbar.legacy.legacygamemanager.world;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

/**
 * Parent class for Minigame Worlds
 */

public abstract class GameWorld extends ServerWorld {
    @Getter
    private static HashMap<World, GameWorld> gameWorlds = new HashMap<>();

    @Getter @Setter
    private LobbyWorld lobbyWorld;

    /**
     *
     * @param inGameWorld -> world template for this specific gameWorld
     * @param lobbyWorld -> lobby for this specific gameWorld - can be null, but must be defined at some point
     */
    public GameWorld(World inGameWorld, @Nullable LobbyWorld lobbyWorld) {
        super(inGameWorld);

        this.lobbyWorld = lobbyWorld;

        if(gameWorlds.containsKey(inGameWorld))
            gameWorlds.replace(inGameWorld, this);
        else
            gameWorlds.put(inGameWorld, this);
    }

    public abstract void start();

    public abstract void finish();

    /**
     * Send players back to lobby
     */

    protected void sendBack() {
        getTemplate().getPlayers().forEach(player
                -> player.teleport(lobbyWorld.getTemplate().getSpawnLocation()));
    }

    public static GameWorld fromWorld(World world) {
        if(world == null)
            return null;
        return gameWorlds.get(world);
    }

    @Override
    public boolean create() {
        return create("GameWorld");
    }
}
