package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.common.block.flower.functional.TangleberrieBlockEntity;

@Mixin(TangleberrieBlockEntity.class)
public class TangleberrieMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.tangleberrieManaCapacity;
    }

    /**
     * @author KonSola5
     * @reason Make Range configurable.
     */
    @Overwrite(remap = false)
    public double getRange() {
        return ConfigFile.tangleberrieRange;
    }

    /**
     * @author KonSola5
     * @reason Make max distance configurable.
     */
    @Overwrite(remap = false)
    public double getMaxDistance() {
        return ConfigFile.tangleberrieMaxDistance;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/TangleberrieBlockEntity;addMana(I)V"))
    private void configureCost(TangleberrieBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.tangleberrieManaCost);
    }
}
