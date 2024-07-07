package com.seanstapletondoyle.metal_detector.item;

import com.seanstapletondoyle.metal_detector.MetalDetectorMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MetalDetectorMod.MODID);
    public static final RegistryObject<Item> OSC_CRYSTAL = ITEMS.register("osc_crystal",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
