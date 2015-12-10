package utils;

import java.util.function.Function;

import library.Tuple2;
import utils.tyvaralg.shared.G_TyVarAlgTransform;

public interface TyMap<Ty> extends G_TyVarAlgTransform<Tuple2<TyMap.VarMapper<Ty>, Integer>, Ty> {
	interface VarMapper<Ty> {
		Ty apply(int c, int x, int n);
	}

	@Override
	default Function<Tuple2<VarMapper<Ty>, Integer>, Ty> TyVar(int x, int n) {
		return pair -> pair._1.apply(pair._2, x, n);
	}
}