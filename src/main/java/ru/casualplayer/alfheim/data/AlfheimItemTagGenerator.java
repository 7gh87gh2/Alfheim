package ru.casualplayer.alfheim.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import ru.casualplayer.alfheim.AlfheimMod;
import ru.casualplayer.alfheim.item.AlfheimModItems;
import ru.casualplayer.alfheim.util.AlfheimTags;

import java.util.concurrent.CompletableFuture;

public class AlfheimItemTagGenerator extends ItemTagsProvider {

    public AlfheimItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> block, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, block, AlfheimMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // Raw Ores
        this.tag(Tags.Items.RAW_MATERIALS)
                .add(AlfheimModItems.RAW_URU_METAL.get())
                .add(AlfheimModItems.RAW_ELEMENTIUM.get())
                .add(AlfheimModItems.RAW_MITHRIL.get())
        ;
        this.tag(AlfheimTags.Items.RAW_URU_METAL).add(AlfheimModItems.RAW_URU_METAL.get());
        this.tag(AlfheimTags.Items.RAW_ELEMENTIUM).add(AlfheimModItems.RAW_ELEMENTIUM.get());
        this.tag(AlfheimTags.Items.RAW_MITHRIL).add(AlfheimModItems.RAW_MITHRIL.get());

        // Nuggets
        this.tag(Tags.Items.NUGGETS)
                .add(AlfheimModItems.URU_METAL_NUGGET.get())
                .add(AlfheimModItems.MITHRIL_NUGGET.get())
        ;
        this.tag(AlfheimTags.Items.NUGGET_URU_METAL).add(AlfheimModItems.URU_METAL_NUGGET.get());
        this.tag(AlfheimTags.Items.NUGGET_MITHRIL).add(AlfheimModItems.MITHRIL_NUGGET.get());

        // Ingots
        this.tag(Tags.Items.INGOTS)
                .add(AlfheimModItems.URU_METAL_INGOT.get())
                .add(AlfheimModItems.MITHRIL_INGOT.get())
        ;
        this.tag(AlfheimTags.Items.INGOT_URU_METAL).add(AlfheimModItems.URU_METAL_INGOT.get());
        this.tag(AlfheimTags.Items.INGOT_MITHRIL).add(AlfheimModItems.MITHRIL_INGOT.get());
    }
}
