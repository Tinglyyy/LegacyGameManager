package de.unverwunderbar.legacy.legacygamemanager.world;

import lombok.Getter;
import org.bukkit.World;

/**
 * Parent class for the Hub World
 */

public abstract class HubWorld extends ServerWorld {
    @Getter
    private static HubWorld hubWorld;

    public HubWorld(World inGameWorld) {
        super(inGameWorld);

        hubWorld = this;
    }

    @Override
    public boolean create() {
        return create("HubWorld");
    }
}
