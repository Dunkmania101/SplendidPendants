package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerInit {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SplendidPendants.modid);

    public static final RegistryObject<MenuType<LocketContainer>> LOCKET_CONTAINER = CONTAINERS
            .register("locket_container", () -> IForgeContainerType.create(LocketContainer::new));

    public static final RegistryObject<MenuType<DyeableContainer>> DYEABLE_CONTAINER = CONTAINERS
            .register("dyeable_container", () -> IForgeContainerType.create(DyeableContainer::new));

    public static void initScreens() {
        MenuScreens.register(LOCKET_CONTAINER.get(), MenuScreens.getScreenFactory(LOCKET_CONTAINER.get(), Minecraft.getInstance(), 0, new TranslatableComponent("container.splendidpendants.locket")).orElse(null));
        MenuScreens.register(DYEABLE_CONTAINER.get(), MenuScreens.getScreenFactory(DYEABLE_CONTAINER.get(), Minecraft.getInstance(), 0, new TranslatableComponent("container.splendidpendants.dyeable")).orElse(null));
    }
}
