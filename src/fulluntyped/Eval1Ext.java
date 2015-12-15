package fulluntyped;

import fulluntyped.extalg.external.ExtAlgMatcher;
import fulluntyped.extalg.shared.ExtAlgQuery;
import fulluntyped.extalg.shared.GExtAlg;

public interface Eval1Ext<Term, Bind> extends ExtAlgQuery<Term, Term>, arith.Eval1<Term>, record.Eval1<Term> {
	IsValExt<Term> isVal();
	@Override
	ExtAlgMatcher<Term, Term> matcher();
	GExtAlg<Term, Term> alg();

	@Override
	default Term TmTimesFloat(Term t1, Term t2) {
		return matcher()
				.TmFloat(f1 -> matcher().TmFloat(f2 -> alg().TmFloat(f1 * f2))
						.otherwise(() -> alg().TmTimesFloat(t1, visitTerm(t2)))
						.visitTerm(t2))
				.otherwise(() -> alg().TmTimesFloat(visitTerm(t1), t2))
				.visitTerm(t1);
	}
}
