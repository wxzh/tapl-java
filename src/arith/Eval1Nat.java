package arith;

import arith.natalg.external.NatAlgMatcher;
import arith.natalg.shared.NatAlg;
import arith.natalg.shared.NatAlgQuery;

public interface Eval1Nat<Term> extends NatAlgQuery<Term, Term> {
	NatAlgMatcher<Term, Term> matcher();
	IsNumericalVal<Term> isNumericalVal();
	NatAlg<Term, Term> alg();

	@Override
	default Term TmPred(Term t) {
		return matcher()
				.TmZero(() -> t)
				.TmSucc(nv1 -> isNumericalVal().visitTerm(nv1) ? nv1 : TmPred(visitTerm(t)))
				.otherwise(() -> alg().TmPred(visitTerm(t)))
				.visitTerm(t);
	}

	@Override
	default Term TmSucc(Term t) {
		return alg().TmSucc(visitTerm(t));
	}
}
