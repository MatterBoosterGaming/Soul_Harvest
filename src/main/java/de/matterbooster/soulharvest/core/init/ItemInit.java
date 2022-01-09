package de.matterbooster.soulharvest.core.init;

import de.matterbooster.soulharvest.Soulharvest;
import de.matterbooster.soulharvest.core.init.customitem.Ring_of_the_Soul_Reaper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {     //Fügt neue Item hinzu
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Soulharvest.MOD_ID);

//    public static final RegistryObject<Item> test = ITEMS.register("test",     //Itemvorlage
 //           () -> new Item(new Item.Properties().group(ItemGroup.COMBAT)));   //itemvorlage

    public static final RegistryObject<Item> RING_OF_THE_SOUL_REAPER = ITEMS.register("ring_of_the_soul_reaper",
            () -> new Ring_of_the_Soul_Reaper((new Item.Properties().group(ItemGroup.TOOLS).maxStackSize(1).defaultMaxDamage(0).rarity(Rarity.EPIC))));//das ring_of_blood ist die Item classe

    public static final RegistryObject<Item> STONE_OF_SOULS = ITEMS.register("stone_of_souls",
              () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SOUL = ITEMS.register("soul",
            () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS).rarity(Rarity.RARE)));
}
