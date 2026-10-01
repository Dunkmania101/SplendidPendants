package dunkmania101.splendidpendants.init;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class TabInit {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister
            .create(Registries.CREATIVE_MODE_TAB, SplendidPendants.modid);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPLENDID_PENDANTS_GROUP = TABS.register(
            "splendidpendants",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.splendidpendants"))
                    .icon(() -> new ItemStack(ItemInit.LOCKET.get()))
                    .displayItems((params, output) -> ItemInit.ITEMS.getEntries()
                            .forEach(entry -> output.accept(entry.get())))
                    .build());
}
