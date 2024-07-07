package com.seanstapletondoyle.metal_detector.block;

import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;

import java.util.function.Supplier;

public class CrystalBlock extends ChestBlock {

    public CrystalBlock(Properties pProps, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(pProps, supplier);
    }
}
