package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.*;
import vazkii.botania.common.block.flower.functional.RannuncarpusBlockEntity;
import vazkii.botania.common.block.flower.functional.TigerseyeBlockEntity;

@Mixin(TigerseyeBlockEntity.class)
public class TigerseyeMixin {
    /**
     * @author KonSola5
     * @reason Make Mana Capacity modifiable.
     */
    @Overwrite(remap = false)
    public int getMaxMana() {
        return ConfigFile.HANDLER.instance().getFunctional().getTigerseye().manaCapacity;
    }

    @ModifyConstant(method = "tickFlower",constant = @Constant(intValue = 70),
            slice = @Slice(
                    from = @At(value = "INVOKE",target = "Lnet/minecraft/world/entity/monster/Creeper;setTarget(Lnet/minecraft/world/entity/LivingEntity;)V"),
                    to = @At(value = "INVOKE",target = "Lvazkii/botania/common/internal_caps/TigerseyeComponent;setPacified()V")),
            remap = false)
    private int configureCost1(int original){
        return ConfigFile.HANDLER.instance().getFunctional().getTigerseye().manaCost;
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lvazkii/botania/common/block/flower/functional/TigerseyeBlockEntity;addMana(I)V"), remap = false)
    private void configureCost2(TigerseyeBlockEntity instance, int i) {
        instance.addMana(-ConfigFile.HANDLER.instance().getFunctional().getTigerseye().manaCost);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 0), remap = false)
    private BlockPos configureRange1(BlockPos instance, int dx, int dy, int dz) {
        final int RANGEXZ = ConfigFile.HANDLER.instance().getFunctional().getTigerseye().rangeXZ;
        final int RANGEY = ConfigFile.HANDLER.instance().getFunctional().getTigerseye().rangeY;
        return instance.offset(-RANGEXZ, -RANGEY, -RANGEXZ);
    }

    @Redirect(method = "tickFlower", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;offset(III)Lnet/minecraft/core/BlockPos;", ordinal = 1), remap = false)
    private BlockPos configureRange2(BlockPos instance, int dx, int dy, int dz) {
        final int RANGEXZ = ConfigFile.HANDLER.instance().getFunctional().getTigerseye().rangeXZ;
        final int RANGEY = ConfigFile.HANDLER.instance().getFunctional().getTigerseye().rangeY;
        return instance.offset(RANGEXZ + 1, RANGEY + 1, RANGEXZ + 1);
    }

    @ModifyArg(method = "getRadius", at = @At(
            value = "INVOKE",
            target = "Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;square(Lnet/minecraft/core/BlockPos;I)Lvazkii/botania/api/block_entity/RadiusDescriptor$Rectangle;"
    ), index = 1, remap = false)
    private int configureRange3(int range) {
        return ConfigFile.HANDLER.instance().getFunctional().getTigerseye().rangeXZ;
    }


}