package ru.casualplayer.alfheim.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;

public class AlfheimModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, AlfheimMod.MODID);

    public static final RegistryObject<Item> RAW_URU_METAL = ITEMS.register("raw_uru_metal", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> URU_METAL_NUGGET = ITEMS.register("uru_metal_nugget", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> URU_METAL_INGOT = ITEMS.register("uru_metal_ingot", ()->new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_ELEMENTIUM = ITEMS.register("raw_elementium", ()->new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAW_MITHRIL = ITEMS.register("raw_mithril", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_NUGGET = ITEMS.register("mithril_nugget", ()->new Item(new Item.Properties()));
    public static final RegistryObject<Item> MITHRIL_INGOT = ITEMS.register("mithril_ingot", ()->new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFUSED_DREAMWOOD_TWIG = ITEMS.register("infused_dreamwood_twig", InfusedDreamwoodTwig::new);

    public static void register(IEventBus bus) { ITEMS.register(bus); }

    public static class AlfheimCreativeTabs {
        public static final DeferredRegister<CreativeModeTab> ALFHEIM_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AlfheimMod.MODID);

        public static final RegistryObject<CreativeModeTab> ALFHEIM_TAB_ITEMS = ALFHEIM_TABS.register("alfheim_tab",
                ()-> CreativeModeTab.builder()
                        .icon(()->new ItemStack(AlfheimModItems.INFUSED_DREAMWOOD_TWIG.get()))
                        .title(Component.translatable("creativetab.alfheim_tab"))
                        .displayItems((parameters, output) -> {
                            // Items
                            output.accept(AlfheimModItems.RAW_URU_METAL.get());
                            output.accept(AlfheimModItems.URU_METAL_NUGGET.get());
                            output.accept(AlfheimModItems.URU_METAL_INGOT.get());
                            output.accept(AlfheimModItems.RAW_ELEMENTIUM.get());
                            output.accept(AlfheimModItems.RAW_MITHRIL.get());
                            output.accept(AlfheimModItems.MITHRIL_NUGGET.get());
                            output.accept(AlfheimModItems.MITHRIL_INGOT.get());
                            output.accept(AlfheimModItems.INFUSED_DREAMWOOD_TWIG.get());

                            // Blocks
                            output.accept(AlfheimModBlocks.ALFHEIM_STONE.get());
                            output.accept(AlfheimModBlocks.ALFHEIM_COBBLESTONE.get());
                            output.accept(AlfheimModBlocks.ALFHEIM_DIRT.get());

                            output.accept(AlfheimModBlocks.RAW_URU_METAL_BLOCK.get());
                            output.accept(AlfheimModBlocks.URU_METAL_ORE.get());
                            output.accept(AlfheimModBlocks.URU_METAL_BLOCK.get());
                            output.accept(AlfheimModBlocks.ELEMENTIUM_ORE.get());
                            output.accept(AlfheimModBlocks.RAW_ELEMENTIUM_BLOCK.get());
                            output.accept(AlfheimModBlocks.DRAGONSTONE_ORE.get());
                            output.accept(AlfheimModBlocks.MITHRIL_ORE.get());
                            output.accept(AlfheimModBlocks.MITHRIL_BLOCK.get());
                            output.accept(AlfheimModBlocks.RAW_MITHRIL_BLOCK.get());

                            output.accept(AlfheimModBlocks.DREAMWOOD_LEAVES.get());
                            output.accept(AlfheimModBlocks.DREAMWOOD_SAPLING.get());

                            output.accept(AlfheimModBlocks.ALFHEIM_PORTAL_CORE.get());
                        })
                        .build());

        public static void register(IEventBus bus) { ALFHEIM_TABS.register(bus); }
    }

}
