//package dunkmania101.splendidpendants.util.networking.packets;
//
//import net.minecraft.entity.EntityType;
//import net.minecraft.entity.SpawnReason;
//import net.minecraft.entity.player.ServerPlayerEntity;
//import net.minecraft.network.PacketBuffer;
//import net.minecraft.server.MinecraftServer;
//import net.minecraft.util.RegistryKey;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.registry.Registry;
//import net.minecraft.world.World;
//import net.minecraft.world.server.ServerWorld;
//import net.minecraftforge.fml.network.NetworkEvent;
//import net.minecraftforge.registries.ForgeRegistries;
//
//import java.util.function.Supplier;
//
//public class PacketSpawnEntity {
//    private final ResourceLocation id;
//    private final RegistryKey<World> type;
//    private final BlockPos pos;
//
//    public PacketSpawnEntity(PacketBuffer buf) {
//        id = buf.readResourceLocation();
//        type = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, buf.readResourceLocation());
//        pos = buf.readBlockPos();
//    }
//
//    public PacketSpawnEntity(ResourceLocation id, RegistryKey<World> type, BlockPos pos) {
//        this.id = id;
//        this.type = type;
//        this.pos = pos;
//    }
//
//    public void toBytes(PacketBuffer buf) {
//        buf.writeResourceLocation(id);
//        buf.writeResourceLocation(type.getLocation());
//        buf.writeBlockPos(pos);
//    }
//
//    public boolean handle(Supplier<NetworkEvent.Context> ctx) {
//        if (ctx.get() != null) {
//            ctx.get().enqueueWork(() -> {
//                ServerPlayerEntity serverPlayerEntity = ctx.get().getSender();
//                if (serverPlayerEntity != null) {
//                    MinecraftServer server = serverPlayerEntity.getEntityWorld().getServer();
//                    if (server != null) {
//                        ServerWorld spawnWorld = server.getWorld(type);
//                        if (spawnWorld != null) {
//                            EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(id);
//                            if (entityType != null) {
//                                entityType.spawn(spawnWorld, null, null, pos, SpawnReason.SPAWN_EGG, true, true);
//                            }
//                        }
//                    }
//                }
//            });
//        }
//        return true;
//    }
//}
