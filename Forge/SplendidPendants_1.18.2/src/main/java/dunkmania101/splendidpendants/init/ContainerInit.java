package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.objects.containers.screens.DyeableScreen;
import dunkmania101.splendidpendants.objects.containers.screens.HoldingScreen;
import dunkmania101.splendidpendants.objects.containers.screens.LocketScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerInit {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SplendidPendants.modid);

    public static final RegistryObject<MenuType<LocketContainer>> LOCKET_CONTAINER = CONTAINERS
            .register("locket_container", () -> IForgeMenuType.create(LocketContainer::new));

    public static final RegistryObject<MenuType<DyeableContainer>> DYEABLE_CONTAINER = CONTAINERS
            .register("dyeable_container", () -> IForgeMenuType.create(DyeableContainer::new));

    public static final RegistryObject<MenuType<HoldingContainer>> HOLDING_CONTAINER = CONTAINERS
            .register("holding_container", () -> IForgeMenuType.create(HoldingContainer::new));

    public static void initScreens() {
        MenuScreens.register(LOCKET_CONTAINER.get(), LocketScreen::new);
        MenuScreens.register(DYEABLE_CONTAINER.get(), DyeableScreen::new);
        MenuScreens.register(HOLDING_CONTAINER.get(), HoldingScreen::new);
    }
}
