package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import dunkmania101.splendidpendants.objects.containers.screens.DyeableScreen;
import dunkmania101.splendidpendants.objects.containers.screens.HoldingScreen;
import dunkmania101.splendidpendants.objects.containers.screens.LocketScreen;

public class ContainerInit {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, SplendidPendants.modid);

    public static final RegistryObject<ContainerType<LocketContainer>> LOCKET_CONTAINER = CONTAINERS
            .register("locket_container", () -> IForgeContainerType.create(LocketContainer::new));

    public static final RegistryObject<ContainerType<DyeableContainer>> DYEABLE_CONTAINER = CONTAINERS
            .register("dyeable_container", () -> IForgeContainerType.create(DyeableContainer::new));

    public static final RegistryObject<ContainerType<HoldingContainer>> HOLDING_CONTAINER = CONTAINERS
            .register("holding_container", () -> IForgeContainerType.create(HoldingContainer::new));

    public static void initScreens() {
        ScreenManager.register(LOCKET_CONTAINER.get(), LocketScreen::new);
        ScreenManager.register(DYEABLE_CONTAINER.get(), DyeableScreen::new);
        ScreenManager.register(HOLDING_CONTAINER.get(), HoldingScreen::new);
    }
}
