package dunkmania101.splendidpendants;

import dunkmania101.splendidpendants.data.CommonConfig;
import dunkmania101.splendidpendants.data.compat.CuriosCompat;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.init.ArmorMaterialInit;
import dunkmania101.splendidpendants.init.ComponentInit;
import dunkmania101.splendidpendants.init.ContainerInit;
import dunkmania101.splendidpendants.init.ItemInit;
import dunkmania101.splendidpendants.init.TabInit;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(SplendidPendants.modid)
public class SplendidPendants {
    public static final String modid = "splendidpendants";

    public SplendidPendants(IEventBus modbus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);

        ComponentInit.COMPONENTS.register(modbus);
        ArmorMaterialInit.ARMOR_MATERIALS.register(modbus);
        ItemInit.ITEMS.register(modbus);
        ContainerInit.CONTAINERS.register(modbus);
        TabInit.TABS.register(modbus);

        modbus.addListener(this::registerCapabilities);
        if (FMLEnvironment.dist.isClient()) {
            modbus.addListener(this::clientSetup);
        }
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        if (Mods.CURIOS.isLoaded()) {
            CuriosCompat.curiosClientSetup();
        }
    }

    private void registerCapabilities(final RegisterCapabilitiesEvent event) {
        if (Mods.CURIOS.isLoaded()) {
            CuriosCompat.registerPendantCapabilities(event);
        }
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(modid, path);
    }
}
