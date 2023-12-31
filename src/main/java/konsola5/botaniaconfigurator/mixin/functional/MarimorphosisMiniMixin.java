package konsola5.botaniaconfigurator.mixin.functional;

import konsola5.botaniaconfigurator.ConfigFile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vazkii.botania.common.block.flower.functional.MarimorphosisBlockEntity;

@Mixin(MarimorphosisBlockEntity.Mini.class)
public class MarimorphosisMiniMixin {
    /**
     * @author KonSola5
     * @reason Make range (X/Z) configurable.
     */
    @Overwrite(remap = false)
    public int getRange() {
        return ConfigFile.marimorphosisRangeXZMini;
    }

    /**
     * @author KonSola5
     * @reason Make range (Y) configurable.
     */
    @Overwrite(remap = false)
    public int getRangeY() {
        return ConfigFile.marimorphosisRangeYMini;
    }
}
