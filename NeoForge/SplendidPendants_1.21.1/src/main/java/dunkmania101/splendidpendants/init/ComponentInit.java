package dunkmania101.splendidpendants.init;

import com.mojang.serialization.Codec;

import dunkmania101.splendidpendants.SplendidPendants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ComponentInit {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister
            .createDataComponents(Registries.DATA_COMPONENT_TYPE, SplendidPendants.modid);

    public static final Supplier<DataComponentType<ItemContainerContents>> STORED_PENDANTS = COMPONENTS
            .registerComponentType("stored_pendants", builder -> builder
                    .persistent(ItemContainerContents.CODEC)
                    .networkSynchronized(ItemContainerContents.STREAM_CODEC));

    public static final Supplier<DataComponentType<ItemContainerContents>> STORED_DYES = COMPONENTS
            .registerComponentType("stored_dyes", builder -> builder
                    .persistent(ItemContainerContents.CODEC)
                    .networkSynchronized(ItemContainerContents.STREAM_CODEC));

    public static final Supplier<DataComponentType<ItemContainerContents>> HELD_ITEMS = COMPONENTS
            .registerComponentType("held_items", builder -> builder
                    .persistent(ItemContainerContents.CODEC)
                    .networkSynchronized(ItemContainerContents.STREAM_CODEC));

    public static final Supplier<DataComponentType<Boolean>> ENABLED = COMPONENTS
            .registerComponentType("enabled", builder -> builder
                    .persistent(Codec.BOOL)
                    .networkSynchronized(ByteBufCodecs.BOOL));

    public static final Supplier<DataComponentType<Integer>> SPONGE_COLOR = COMPONENTS
            .registerComponentType("sponge_color", builder -> builder
                    .persistent(Codec.INT)
                    .networkSynchronized(ByteBufCodecs.INT));
}
