package typed;

import typed.termalg.external.TermAlgMatcher;
import typed.termalg.shared.GTermAlg;
import typed.termalg.shared.TermAlgQuery;

public interface Eval1<Term, Ty> extends TermAlgQuery<Term, Ty, Term>, varapp.Eval1<Term> {
	@Override
	GTermAlg<Term, Ty, Term> alg();
	@Override
	TermShiftAndSubst<Term, Ty> termShiftAndSubst();
	@Override
	IsVal<Term, Ty> isVal();
	@Override
	TermAlgMatcher<Term, Ty, Term> matcher();
}
