package arith;

import arith.termalg.external.TermAlgMatcher;
import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import utils.ZeroNoRuleApplies;

public interface Eval1<Term> extends TermAlgQuery<Term, Term>, Eval1Bool<Term>, Eval1Nat<Term> {
	TermAlgMatcher<Term, Term> matcher();
	arith.termalg.shared.TermAlg<Term, Term> alg();
	IsNumericVal<Term> isNumericVal();

	default Zero<Term> m() {
		return new ZeroNoRuleApplies<>();
	}

	@Override
	default Term TmIsZero(Term t) {
		return matcher()
				.TmZero(() -> alg().TmTrue())
				.TmSucc(nv1 -> isNumericVal().visitTerm(nv1) ? alg().TmFalse() : alg().TmIsZero(visitTerm(t)))
				.otherwise(() -> alg().TmIsZero(visitTerm(t)))
				.visitTerm(t);
	}
}