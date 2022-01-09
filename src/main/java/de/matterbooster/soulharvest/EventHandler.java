package de.matterbooster.soulharvest;
import de.matterbooster.soulharvest.loottabels.SoulstructureAdditon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = Soulharvest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
        new SoulstructureAdditon.Serializer().setRegistryName
                (new ResourceLocation(Soulharvest.MOD_ID,"soul_from_simpledungeon"))

        );
    }
}