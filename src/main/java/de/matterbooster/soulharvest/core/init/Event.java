package de.matterbooster.soulharvest.core.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import top.theillusivec4.curios.api.CuriosApi;
import static de.matterbooster.soulharvest.core.init.ItemInit.RING_OF_THE_SOUL_REAPER;

public class Event {
    @SubscribeEvent
    public void onLivingDeathEvent(LivingDeathEvent event) {        //Checkt bei Tod eines Lebewesen

        ItemStack heldItem2;
        if (!event.getEntity().getEntityWorld().isRemote) {
            Entity killer = event.getSource().getTrueSource();      //bekommt den Mörder raus
            if (killer instanceof PlayerEntity) {
                if (CuriosApi.getCuriosHelper().findEquippedCurio(RING_OF_THE_SOUL_REAPER.get(), (LivingEntity) killer).isPresent()) {            //wenn der spieler das Item Soul ring hat
                    heldItem2 = CuriosApi.getCuriosHelper().findEquippedCurio(RING_OF_THE_SOUL_REAPER.get(), (LivingEntity) killer).get().getRight();
                    CompoundNBT tag = new CompoundNBT();        //abhier aufteilung ins SPieler und Mobs
                    if(event.getEntity() instanceof PlayerEntity){
                    if (heldItem2.hasTag()) {
                        heldItem2.getTag().putInt("KillCounterRingPlayer", heldItem2.getTag().getInt("KillCounterRingPlayer") + 1);
                    } else {
                        tag.putInt("KillCounterRingPlayer", 1);
                        heldItem2.setTag(tag);

                    }
                } // if(event.getEntity() instanceof IMob) {            //Aktivieren wenn reichweite hinzugefügt wurde
                   //     if (heldItem2.hasTag()) {
                    //        heldItem2.getTag().putInt("KillCounterRingMonster", heldItem2.getTag().getInt("KillCounterRingMonster") + 1);
                      //  } else {
                    //        tag.putInt("KillCounterRingMonster", 1);
                     //       heldItem2.setTag(tag);

                    else {
                        if (heldItem2.hasTag()) {
                            heldItem2.getTag().putInt("KillCounterRingMob", heldItem2.getTag().getInt("KillCounterRingMob") + 1);
                        } else {
                            tag.putInt("KillCounterRingMob", 1);
                            heldItem2.setTag(tag);

                        }
            }
        }

    }

}}}

