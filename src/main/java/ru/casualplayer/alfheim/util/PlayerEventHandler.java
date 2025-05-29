package ru.casualplayer.alfheim.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import ru.casualplayer.alfheim.AlfheimMod;

@Mod.EventBusSubscriber(modid = AlfheimMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class PlayerEventHandler {

    public static final String TELEPORT_COOLDOWN_KEY = "AlfheimTeleportCooldown";
    public static final String TIME_OF_LAST_TELEPORT = "TimeOfLastAlfheimTeleport";

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        CompoundTag persistentData = event.getEntity().getPersistentData();
        persistentData.putLong(TELEPORT_COOLDOWN_KEY, 0);
        persistentData.putLong(TIME_OF_LAST_TELEPORT, 0);
    }
}