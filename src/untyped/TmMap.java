package untyped;

import java.util.function.Function;

import library.Tuple2;
import untyped.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term> extends G_TermAlgTransform<Tuple2<Function<Integer, Function<Integer, Function<Integer, Term>>>, Integer>, Term> {
	default Function<Tuple2<Function<Integer, Function<Integer, Function<Integer, Term>>>, Integer>, Term> TmVar(int x, int n) {
		return pair -> pair._1.apply(pair._2).apply(x).apply(n);
	}

	default Function<Tuple2<Function<Integer, Function<Integer, Function<Integer, Term>>>, Integer>, Term> TmAbs(String x, Term t) {
		return pair -> alg().TmAbs(x, visitTerm(t).apply(new Tuple2<>(pair._1, pair._2 + 1)));
	}
}