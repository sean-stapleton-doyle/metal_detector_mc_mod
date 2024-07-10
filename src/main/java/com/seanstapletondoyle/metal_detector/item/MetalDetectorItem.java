package com.seanstapletondoyle.metal_detector.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class MetalDetectorItem extends Item {
    private static final int DETECTION_DISTANCE = 12;
    private static final TagKey<Block> METAL_BLOCKS_TAG = BlockTags.create(new ResourceLocation("metal_detector", "metal_blocks"));

    public MetalDetectorItem(Properties props) {
        super(props);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            detectIronOre(level, player);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    private void detectIronOre(Level level, Player player) {
        Vec3 playerLookVector = player.getLookAngle();
        Vec3 playerPosition = player.getEyePosition(1.0f);

        for (int i = 1; i <= DETECTION_DISTANCE; i++) {
            Vec3 checkVector = playerPosition.add(playerLookVector.scale(i));

            for (int xOffset = -1; xOffset <= 2; xOffset++) {
                for (int yOffset = -1; yOffset <= 2; yOffset++) {
                    BlockPos positionToCheck = new BlockPos(
                            (int) checkVector.x + xOffset,
                            (int) checkVector.y + yOffset,
                            (int) checkVector.z);

                    if (blockHasMetal(level, positionToCheck)) {
                        makeSound(level, positionToCheck);
                        player.sendSystemMessage(Component.literal("Metal detected at " +
                                "(" + positionToCheck.getX() + ", " + positionToCheck.getY() + ", " + positionToCheck.getZ() + ")"));
                        return;
                    }
                }
            }
        }
    }

    private boolean blockHasMetal(Level level, BlockPos blockPosition) {
        BlockState blockState = level.getBlockState(blockPosition);
        return blockState.is(METAL_BLOCKS_TAG);
    }

    private void makeSound(Level level, BlockPos blockPos) {
        level.playSound(
                null, // Null: no restriction on who hears the sound
                blockPos,
                SoundEvents.BELL_BLOCK,
                SoundSource.PLAYERS,
                1.0f, // Volume
                1.0f // Pitch
        );
    }
}
