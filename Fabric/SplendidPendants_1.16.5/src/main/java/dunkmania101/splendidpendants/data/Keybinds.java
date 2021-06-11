package dunkmania101.splendidpendants.data;

import dunkmania101.splendidpendants.util.Tools;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class Keybinds {
    public static final KeyBinding summonKey = registerKey("itemGroup.splendidpendants",
            "key.splendidpendants.summonKey",
            GLFW.GLFW_KEY_LEFT_ALT);

    public static KeyBinding registerKey(String categoryIn, String descriptionIn, int idIn) {
        return registerKey(new KeyBinding(Tools.translateString(descriptionIn), KeyConflictContext.IN_GAME, InputMappings.Type.KEYSYM,
                idIn, Tools.translateString(categoryIn)));
    }

    public static KeyBinding registerKey(KeyBinding bindIn) {
        ClientRegistry.registerKeyBinding(bindIn);
        return bindIn;
    }
}
