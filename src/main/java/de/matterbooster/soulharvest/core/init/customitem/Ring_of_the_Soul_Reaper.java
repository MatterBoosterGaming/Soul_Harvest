package de.matterbooster.soulharvest.core.init.customitem;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import de.matterbooster.soulharvest.Soulharvest;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;


public class Ring_of_the_Soul_Reaper extends Item implements ICurioItem {
    public Ring_of_the_Soul_Reaper(Properties properties) {
        super(properties);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) { //fügt Lore Text zum Item Hinzu
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.hasTag()) {
            if(Screen.hasShiftDown()){
                tooltip.add(new TranslationTextComponent("toolinfo.soulharvest.ring_of_the_soul_reaper.information"));
                tooltip.add(new TranslationTextComponent("toolinfo.soulharvest.ring_of_the_soul_reaper.information1"));

            }else {
            tooltip.add(new TranslationTextComponent("toolinfo.soulharvest.ring_of_the_soul_reaper.lore"));  //Lore
            tooltip.add(new StringTextComponent("\u00A74" + "Playerkills: " + stack.getTag().getInt("KillCounterRingPlayer")));
            tooltip.add(new StringTextComponent("\u00A79" + "Monsterkills: " + stack.getTag().getInt("KillCounterRingMonster")));
            tooltip.add(new StringTextComponent("\u00A7a" + "Mobkills: " + stack.getTag().getInt("KillCounterRingMob")));
            tooltip.add(new TranslationTextComponent("toolinfo.soulharvest.ring_of_the_soul_reaper.ShiftDruecken"));    //Drücke Shift
        }}
    }



    @Override
   public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {    //gibt dem Item ein Effekt beim Tragen
            if (!livingEntity.getEntityWorld().isRemote && livingEntity.ticksExisted % 19 == 0) {
                livingEntity.addPotionEffect(new EffectInstance(Effects.SPEED, 20, 0, true, false));
            }
        }
    @Nonnull
    @Override
    public ICurio.DropRule getDropRule(LivingEntity livingEntity, ItemStack stack) {    //macht das das Item beim Tod behalten wird
        return ICurio.DropRule.ALWAYS_KEEP;
    }
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext,
                                                                        UUID uuid,ItemStack stack) {

        Multimap<Attribute, AttributeModifier> atts = LinkedHashMultimap.create();
        if (stack.hasTag()){ //gibt dem Item wenn es einen Tag hat diese werte
        atts.put(Attributes.ATTACK_SPEED,
                new AttributeModifier(uuid, Soulharvest.MOD_ID + ":speed_bonus", (Math.sqrt(stack.getTag().getInt("KillCounterRingPlayer")*2) + stack.getTag().getInt("KillCounterRingPlayer")) / 1250,
                        AttributeModifier.Operation.MULTIPLY_TOTAL));
      //  atts.put(Attributes.ATTACK_DAMAGE,        //wenn reichweite hinzugefügt Aktivieren
        //        new AttributeModifier(uuid, Ringofblood.MOD_ID + ":damage_bonus", stack.getTag().getInt("KillCounterRingMonster"),
         //               AttributeModifier.Operation.MULTIPLY_TOTAL));
            atts.put(Attributes.ATTACK_DAMAGE,      //Wenn hinzugefügt zu reichweite ändern

                    new AttributeModifier(uuid, Soulharvest.MOD_ID + ":reach_bonus", (Math.sqrt((stack.getTag().getInt("KillCounterRingMob") + stack.getTag().getInt("KillCounterRingMonster"))*2) + (stack.getTag().getInt("KillCounterRingMob") + stack.getTag().getInt("KillCounterRingMonster"))) / 2500,
                            AttributeModifier.Operation.MULTIPLY_TOTAL));
        return atts;
    }

        return atts;
    }
    @Override
    public boolean canEquipFromUse(SlotContext slot, ItemStack stack) { //macht das mit rechtsclick auf boden item ausgerüstet wird
        return true;
    }

}
