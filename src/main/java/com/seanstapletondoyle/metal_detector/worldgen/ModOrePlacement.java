package com.seanstapletondoyle.metal_detector.worldgen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModOrePlacement {
    public static List<PlacementModifier> orePlacement(PlacementModifier placementModifier, PlacementModifier placementModifier1) {
        return List.of(placementModifier, InSquarePlacement.spread(), placementModifier1, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int blockCount, PlacementModifier pHeightRange) {
        return orePlacement(CountPlacement.of(blockCount), pHeightRange);
    }

    public static List<PlacementModifier> rareOrePlacement(int orePlacementProbability, PlacementModifier pHeightRange) {
        return orePlacement(RarityFilter.onAverageOnceEvery(orePlacementProbability), pHeightRange);
    }
}