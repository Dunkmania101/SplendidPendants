package dunkmania101.splendidpendants;

import dunkmania101.splendidpendants.data.CommonConfig;
import dunkmania101.splendidpendants.data.compat.CuriosCompat;
import dunkmania101.splendidpendants.data.compat.Mods;
import dunkmania101.splendidpendants.init.ContainerInit;
import dunkmania101.splendidpendants.init.ItemInit;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

import javax.annotation.Nonnull;

@Mod(SplendidPendants.modid)
public class SplendidPendants {
    public static final String modid = "splendidpendants";

    public SplendidPendants() {
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::setup);
        modbus.addListener(this::enqueueImc);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.CONFIG);
        CommonConfig.init(FMLPaths.CONFIGDIR.get().resolve(SplendidPendants.modid + "-common.toml"));

        ItemInit.ITEMS.register(modbus);
        ContainerInit.CONTAINERS.register(modbus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ContainerInit.initScreens();
    }

    public void enqueueImc(InterModEnqueueEvent event) {
        if (Mods.CURIOS.isLoaded())
            CuriosCompat.enqueueImc();
    }

    @Mod.EventBusSubscriber(modid = SplendidPendants.modid, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class TextureStitch {
        @SubscribeEvent
        public static void stitchTextures(TextureStitchEvent.Pre event) {
            if (event.getMap().getTextureLocation() == PlayerContainer.LOCATION_BLOCKS_TEXTURE) {
                event.addSprite(new ResourceLocation(SplendidPendants.modid, "gui/pendant_slot"));
                event.addSprite(new ResourceLocation(SplendidPendants.modid, "gui/dye_slot"));
            }
        }
    }

    public static class SPLENDID_PENDANTS_GROUP extends ItemGroup {
        public static final ItemGroup instance = new SPLENDID_PENDANTS_GROUP(ItemGroup.GROUPS.length, "splendidpendants");

        private SPLENDID_PENDANTS_GROUP(int index, String label) {
            super(index, label);
        }

        @Nonnull
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ItemInit.LOCKET.get());
        }
    }
}
