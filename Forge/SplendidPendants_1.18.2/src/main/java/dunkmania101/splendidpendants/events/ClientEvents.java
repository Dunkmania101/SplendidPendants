//package dunkmania101.splendidpendants.events;
//
//import dunkmania101.splendidpendants.SplendidPendants;
//import dunkmania101.splendidpendants.util.KeybindTools;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.event.TickEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//@Mod.EventBusSubscriber(modid = SplendidPendants.modid, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
//public class ClientEvents {
//    @OnlyIn(Dist.CLIENT)
//    @SubscribeEvent
//    public void onTick(TickEvent.ClientTickEvent event) {
//        if (event.phase == TickEvent.Phase.START) {
//            KeybindTools.onTick();
//        }
//    }
//}
