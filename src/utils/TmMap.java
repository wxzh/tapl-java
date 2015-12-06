package utils;

import java.util.function.Function;

import library.Tuple2;
import utils.varalg.shared.G_VarAlgTransform;

public interface TmMap<Term> extends G_VarAlgTransform<Tuple2<TmMap.VarMapper<Term>, Integer>, Term> {
	interface VarMapper<Term> {
		Term apply(int c, int x, int n);
	}
	default Function<Tuple2<VarMapper<Term>, Integer>, Term> TmVar(int x, int n) {
		return pair -> pair._1.apply(pair._2, x, n);
	}
}