package ru.casualplayer.alfheim.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.block.AlfheimModBlocks;
import ru.casualplayer.alfheim.item.AlfheimModItems;

public class AlfheimItemModelGenerator extends ItemModelProvider {


    public AlfheimItemModelGenerator(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AlfheimMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        generated(AlfheimModItems.RAW_URU_METAL);
        generated(AlfheimModItems.URU_METAL_NUGGET);
        generated(AlfheimModItems.URU_METAL_INGOT);
        generated(AlfheimModItems.RAW_ELEMENTIUM);
        generated(AlfheimModItems.RAW_MITHRIL);
        generated(AlfheimModItems.MITHRIL_NUGGET);
        generated(AlfheimModItems.MITHRIL_INGOT);

        handheld(AlfheimModItems.INFUSED_DREAMWOOD_TWIG);

        saplingItem(AlfheimModBlocks.DREAMWOOD_SAPLING);

    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "block/"+item.getId().getPath()));
    }

    private ItemModelBuilder generated(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "item/"+item.getId().getPath()));
    }
    private ItemModelBuilder handheld(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(AlfheimMod.MODID, "item/"+item.getId().getPath()));
    }
}
