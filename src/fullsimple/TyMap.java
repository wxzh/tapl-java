package fullsimple;

import fullsimple.tyalg.shared.G_TyAlgTransform;
import library.Tuple2;

public interface TyMap<Ty> extends G_TyAlgTransform<Tuple2<utils.TyMap.VarMapper<Ty>, Integer>, Ty>, utils.TyMap<Ty> {
}