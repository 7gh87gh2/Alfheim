package ru.casualplayer.alfheim.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.alfheim_portal.AlfheimPortalBlock;
import ru.casualplayer.alfheim.block.alfheim_portal.AlfheimPortalCore;
import ru.casualplayer.alfheim.item.AlfheimModItems;

import java.util.function.Supplier;

public class AlfheimModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, AlfheimMod.MODID);

    // Terrain Blocks
    public static final RegistryObject<Block> ALFHEIM_STONE = registerBlock("alfheim_stone", AlfheimModStone::new);
    public static final RegistryObject<Block> ALFHEIM_COBBLESTONE = registerBlock("alfheim_cobblestone", AlfheimModStone::new);
    public static final RegistryObject<Block> ALFHEIM_DIRT = registerBlock("alfheim_dirt", AlfheimModDirt::new);

    // Tree
    public static final RegistryObject<Block> DREAMWOOD_LEAVES = registerBlock("dreamwood_leaves", DreamwoodLeaves::new);
    public static final RegistryObject<Block> DREAMWOOD_SAPLING = registerBlock("dreamwood_sapling", DreamwoodSapling::new);

    // Ores
    public static final RegistryObject<Block> URU_METAL_ORE = registerBlock("uru_metal_ore", ()->new AlfheimModOreBlock(1, 3));
    public static final RegistryObject<Block> ELEMENTIUM_ORE = registerBlock("elementium_ore", ()->new AlfheimModOreBlock(2, 4));
    public static final RegistryObject<Block> DRAGONSTONE_ORE = registerBlock("dragonstone_ore", ()->new AlfheimModOreBlock(4, 5));
    public static final RegistryObject<Block> MITHRIL_ORE = registerBlock("mithril_ore", ()->new AlfheimModOreBlock(9, 12));

    // Ore Blocks
    public static final RegistryObject<Block> URU_METAL_BLOCK = registerBlock("uru_metal_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_URU_METAL_BLOCK = registerBlock("raw_uru_metal_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> RAW_ELEMENTIUM_BLOCK = registerBlock("raw_elementium_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> MITHRIL_BLOCK = registerBlock("mithril_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> RAW_MITHRIL_BLOCK = registerBlock("raw_mithril_block", ()->new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));

    // Portal
    public static final RegistryObject<Block> ALFHEIM_PORTAL_CORE = registerBlock("alfheim_portal_core", AlfheimPortalCore::new);
    public static final RegistryObject<Block> ALFHEIM_PORTAL_BLOCK = registerBlock("alfheim_portal_block", AlfheimPortalBlock::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> supplier) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        registerBlockItem(name, block);
        return block;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        AlfheimModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus bus) { BLOCKS.register(bus); }

}
