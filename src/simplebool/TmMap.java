package simplebool;

import java.util.function.Function;

import library.Tuple2;
import simplebool.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term, Ty> extends G_TermAlgTransform<Tuple2<utils.TmMap.VarMapper<Term>, Integer>, Term, Ty>, utils.TmMap<Term> {
	default Function<Tuple2<VarMapper<Term>, Integer>, Term> TmAbs(String x, Ty ty, Term t) {
		return pair -> alg().TmAbs(x, ty, visitTerm(t).apply(new Tuple2<>(pair._1, pair._2 + 1)));
	}
}