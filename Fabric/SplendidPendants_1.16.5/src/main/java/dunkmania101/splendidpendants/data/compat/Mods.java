package dunkmania101.splendidpendants.data.compat;

import dunkmania101.splendidpendants.data.CommonConfig;
import net.minecraftforge.fml.ModList;

public enum Mods {
    CURIOS("curios", CommonConfig.ENABLE_CURIOS.get());

    private final boolean loaded;
    private final boolean enabled;

    Mods(String modid, boolean enable) {
        this.loaded = ModList.get() != null && ModList.get().getModContainerById(modid).isPresent();
        this.enabled = enable;
    }

    public boolean isLoaded() {
        return this.loaded && this.enabled;
    }
}
