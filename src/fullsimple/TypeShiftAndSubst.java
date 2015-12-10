package fullsimple;

import fullsimple.tyalg.shared.TyAlg;
import utils.TyMap;

public interface TypeShiftAndSubst<Ty> extends utils.TypeShiftAndSubst<Ty> {
	TyMap<Ty> tyMap();
	TyAlg<Ty, Ty> tyAlg();
}
