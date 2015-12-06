package untyped;

import java.util.function.Function;

import library.Tuple2;
import untyped.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term>
		extends G_TermAlgTransform<Tuple2<utils.TmMap.VarMapper<Term>, Integer>, Term>, utils.TmMap<Term> {
	default Function<Tuple2<VarMapper<Term>, Integer>, Term> TmAbs(String x, Term t) {
		return pair -> alg().TmAbs(x, visitTerm(t).apply(new Tuple2<>(pair._1, pair._2 + 1)));
	}
}