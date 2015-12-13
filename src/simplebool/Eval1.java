package simplebool;

import arith.Eval1Bool;
import simplebool.termalg.external.TermAlgMatcher;
import simplebool.termalg.shared.TermAlgQuery;

public interface Eval1<Term, Ty> extends TermAlgQuery<Term, Ty, Term>, Eval1Bool<Term> {
	TermShiftAndSubst<Term, Ty> termShiftAndSubst();
	IsVal<Term, Ty> isVal();
	@Override
	simplebool.termalg.shared.TermAlg<Term, Ty, Term> alg();
	@Override
	TermAlgMatcher<Term, Ty, Term> matcher();


	default Term TmApp(Term t1, Term t2) {
		return matcher()
				.TmAbs(x -> ty -> t -> isVal().visitTerm(t2) ? termShiftAndSubst().termSubstTop(t2, t)
						: (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(() -> alg().TmApp(visitTerm(t1), t2)).visitTerm(t1);
	}
}
