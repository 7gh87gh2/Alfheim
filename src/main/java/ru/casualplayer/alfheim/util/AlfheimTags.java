package ru.casualplayer.alfheim.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import ru.casualplayer.alfheim.AlfheimMod;

public class AlfheimTags {

    public static class Blocks {

        public static final TagKey<Block> ORE_URU_METAL = ore("uru_metal");
        public static final TagKey<Block> ORE_ELEMENTIUM = ore("elementium");
        public static final TagKey<Block> ORE_DRAGONSTONE = ore("dragonstone");
        public static final TagKey<Block> ORE_MITHRIL = ore("mithril");

        public static final TagKey<Block> STORAGE_BLOCK_URU_METAL = storage("uru_metal");
        public static final TagKey<Block> STORAGE_BLOCK_RAW_URU_METAL = storage("raw_uru_metal");
        public static final TagKey<Block> STORAGE_BLOCK_RAW_ELEMENTIUM = storage("raw_elementium");
        public static final TagKey<Block> STORAGE_BLOCK_MITHRIL = storage("mithril");
        public static final TagKey<Block> STORAGE_BLOCK_RAW_MITHRIL = storage("raw_mithril");

        private static TagKey<Block> tag(String name) { return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name)); }
        private static TagKey<Block> ore(String name) { return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", "ore/"+name)); }
        private static TagKey<Block> storage(String name) { return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", "storage_blocks/"+name)); }
    }

    public static class Items {

        public static TagKey<Item> RAW_URU_METAL = raw("uru_metal");
        public static TagKey<Item> RAW_ELEMENTIUM = raw("elementium");
        public static TagKey<Item> RAW_MITHRIL = raw("mithril");

        public static TagKey<Item> NUGGET_URU_METAL = nugget("uru_metal");
        public static TagKey<Item> NUGGET_MITHRIL = nugget("mithril");

        public static TagKey<Item> INGOT_URU_METAL = ingot("uru_metal");
        public static TagKey<Item> INGOT_MITHRIL = ingot("mithril");

        private static TagKey<Item> tag(String name) { return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name)); }
        private static TagKey<Item> raw(String name) { return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "raw_materials/"+name)); }
        private static TagKey<Item> nugget(String name) { return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "nuggets/"+name)); }
        private static TagKey<Item> ingot(String name) { return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", "ingots/"+name)); }
    }

    public static class Biomes {

        public static TagKey<Biome> ALFHEIM_BIOMES = register("alfheim_biomes");

        private static TagKey<Biome> register(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, name));
        }
    }
}
