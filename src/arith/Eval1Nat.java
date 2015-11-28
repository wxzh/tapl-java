package arith;

import arith.natalg.external.NatAlgMatcher;
import arith.natalg.shared.NatAlgTransform;
import utils.NoRuleApplies;

public interface Eval1Nat<Term> extends NatAlgTransform<Term> {
	NatAlgMatcher<Term, Term> matcher();
	IsNumericalVal<Term> isNumericalVal();

	@Override
	default Term TmPred(Term t) {
		return matcher()
				.TmZero(() -> t)
				.TmSucc(nv1 -> isNumericalVal().visitTerm(nv1) ? nv1 : TmPred(visitTerm(t)))
				.otherwise(() -> alg().TmPred(visitTerm(t)))
				.visitTerm(t);
	}

	@Override
	default Term TmZero() {
		throw new NoRuleApplies("TmZero");
	}
}
