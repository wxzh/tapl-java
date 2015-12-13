package arith;

import arith.natalg.external.NatAlgMatcher;
import arith.natalg.shared.NatAlg;
import arith.natalg.shared.NatAlgQuery;
import library.Zero;
import utils.NoRuleApplies;

public interface Eval1Nat<Term> extends NatAlgQuery<Term, Term> {
	NatAlgMatcher<Term, Term> matcher();
	IsNumericVal<Term> isNumericVal();
	NatAlg<Term, Term> alg();

	@Override
	default Zero<Term> m() {
		throw new NoRuleApplies();
	}

	@Override
	default Term TmPred(Term t) {
		return matcher()
				.TmZero(() -> t)
				.TmSucc(nv1 -> isNumericVal().visitTerm(nv1) ? nv1 : TmPred(visitTerm(t)))
				.otherwise(() -> alg().TmPred(visitTerm(t)))
				.visitTerm(t);
	}

	@Override
	default Term TmSucc(Term t) {
		return alg().TmSucc(visitTerm(t));
	}
}
