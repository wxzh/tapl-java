package arith;

import arith.boolalg.external.BoolAlgMatcher;
import arith.boolalg.shared.BoolAlgQuery;

public interface Eval1Bool<I> extends BoolAlgQuery<I, I> {
	arith.boolalg.shared.BoolAlg<I, I> alg();
	BoolAlgMatcher<I, I> matcher();

	@Override
	default I TmIf(I t1, I t2, I t3) {
		return matcher()
				.TmTrue(() -> t2)
				.TmFalse(() -> t3)
				.otherwise(() -> alg().TmIf(visitTerm(t1), t2, t3))
				.visitTerm(t1);
	}
}
