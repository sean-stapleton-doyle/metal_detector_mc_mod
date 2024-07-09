package com.seanstapletondoyle.metal_detector.block;

import com.seanstapletondoyle.metal_detector.MetalDetectorMod;
import com.seanstapletondoyle.metal_detector.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MetalDetectorMod.MODID);

    public static final RegistryObject<Block> CRYSTAL_ORE_BLOCK = registerBlock("crystal_ore",
            () -> new CrystalBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK))
    );
    public static <B extends Block> void registerBlockItem(String name, RegistryObject<B> blockRegistryObject) {
        ModItems.ITEMS.register(name,
                () -> new BlockItem(blockRegistryObject.get(), new Item.Properties())
        );
    }

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<B> blockSupplier){
        RegistryObject<B> blockRegistryObject = BLOCKS.register(name, blockSupplier);
        registerBlockItem(name, blockRegistryObject);
        return blockRegistryObject;
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
