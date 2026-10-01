package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import dunkmania101.splendidpendants.objects.containers.DyeableContainer;
import dunkmania101.splendidpendants.objects.containers.HoldingContainer;
import dunkmania101.splendidpendants.objects.containers.LocketContainer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ContainerInit {
    public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister
            .create(Registries.MENU, SplendidPendants.modid);

    public static final DeferredHolder<MenuType<?>, MenuType<LocketContainer>> LOCKET_CONTAINER = CONTAINERS
            .register("locket_container", () -> IMenuTypeExtension.create(LocketContainer::new));

    public static final DeferredHolder<MenuType<?>, MenuType<DyeableContainer>> DYEABLE_CONTAINER = CONTAINERS
            .register("dyeable_container", () -> IMenuTypeExtension.create(DyeableContainer::new));

    public static final DeferredHolder<MenuType<?>, MenuType<HoldingContainer>> HOLDING_CONTAINER = CONTAINERS
            .register("holding_container", () -> IMenuTypeExtension.create(HoldingContainer::new));
}
