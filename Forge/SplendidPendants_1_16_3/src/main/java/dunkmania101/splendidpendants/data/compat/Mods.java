package dunkmania101.splendidpendants.data.compat;

import net.minecraftforge.fml.ModList;

public enum Mods {
    CURIOS("curios");

    private final boolean loaded;

    Mods(String modid) {
        this.loaded = ModList.get() != null && ModList.get().getModContainerById(modid).isPresent();
    }

    public boolean isLoaded() {
        return this.loaded;
    }
}
