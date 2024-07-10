package com.seanstapletondoyle.metal_detector;

import net.minecraft.core.Vec3i;

public class ModUtils {
    public static <V extends Vec3i> String vectorLikeToString(V vec) {
        return "(" + vec.getX() + ", " + vec.getY() + ", " + vec.getZ() + ")";
    }
}
