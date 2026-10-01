package dunkmania101.splendidpendants.data.compat;

import dunkmania101.splendidpendants.data.CommonConfig;
import net.neoforged.fml.ModList;

public enum Mods {
    CURIOS("curios");

    private final String modid;
    private Boolean loaded = null;

    Mods(String modid) {
        this.modid = modid;
    }

    public boolean isLoaded() {
        if (this.loaded == null) {
            ModList modList = ModList.get();
            if (modList == null) {
                return false;
            }
            this.loaded = modList.getModContainerById(this.modid).isPresent();
        }
        return this.loaded && CommonConfig.getOrDefault(CommonConfig.ENABLE_CURIOS, true);
    }
}
