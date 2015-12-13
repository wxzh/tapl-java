package arith;

import arith.boolalg.external.BoolAlgMatcher;
import arith.boolalg.shared.BoolAlgQuery;
import library.Zero;
import utils.NoRuleApplies;

public interface Eval1Bool<Term> extends BoolAlgQuery<Term, Term> {
	arith.boolalg.shared.BoolAlg<Term, Term> alg();
	BoolAlgMatcher<Term, Term> matcher();

	@Override
	default Zero<Term> m() {
		throw new NoRuleApplies();
	}

	@Override
	default Term TmIf(Term t1, Term t2, Term t3) {
		return matcher()
				.TmTrue(() -> t2)
				.TmFalse(() -> t3)
				.otherwise(() -> alg().TmIf(visitTerm(t1), t2, t3))
				.visitTerm(t1);
	}
}
