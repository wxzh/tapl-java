package fulluntyped;

import java.util.function.Function;

import fulluntyped.termalg.shared.G_TermAlgTransform;
import library.Tuple2;

public interface TmMap<Term> extends
		G_TermAlgTransform<Tuple2<Function<Integer, Function<Integer, Function<Integer, Term>>>, Integer>, Term>,
		untyped.TmMap<Term> {


	default Function<Tuple2<Function<Integer, Function<Integer, Function<Integer, Term>>>, Integer>, Term> TmApp(Term t1, Term t2) {
		return pair -> alg().TmApp(visitTerm(t1).apply(pair), visitTerm(t2).apply(pair));
	}
}
