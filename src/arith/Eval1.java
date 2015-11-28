package arith;

import arith.termalg.external.TermAlgMatcher;
import arith.termalg.shared.TermAlgTransform;

public interface Eval1<Term> extends TermAlgTransform<Term>, Eval1Bool<Term>, Eval1Nat<Term> {
	TermAlgMatcher<Term, Term> matcher();
	IsNumericalVal<Term> isNumericalVal();

	@Override
	default Term TmIsZero(Term t) {
		return matcher()
				.TmZero(() -> alg().TmTrue())
				.TmSucc(nv1 -> isNumericalVal().visitTerm(nv1) ? alg().TmFalse() : alg().TmIsZero(visitTerm(t)))
				.otherwise(() -> alg().TmIsZero(visitTerm(t)))
				.visitTerm(t);
	}
}