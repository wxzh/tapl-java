package simplebool;

import arith.Eval1Bool;
import simplebool.termalg.external.TermAlgMatcher;
import simplebool.termalg.shared.TermAlgQuery;
import utils.TermShiftAndSubst;

public interface Eval1<Term, Ty> extends TermAlgQuery<Term, Ty, Term>, Eval1Bool<Term>, TermShiftAndSubst<Term> {
	IsVal<Term, Ty> isVal();
	@Override
	simplebool.termalg.shared.TermAlg<Term, Ty, Term> alg();
	@Override
	TermAlgMatcher<Term, Ty, Term> matcher();
	@Override
	TmMap<Term, Ty> tmMap();


	default Term TmApp(Term t1, Term t2) {
		return matcher()
				.TmAbs(x -> ty -> t -> isVal().visitTerm(t2) ? termSubstTop(t2, t)
						: (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(() -> alg().TmApp(visitTerm(t1), t2)).visitTerm(t1);
	}
}
