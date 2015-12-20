package untyped;

import untyped.termalg.external.TermAlgMatcher;
import untyped.termalg.shared.GTermAlg;
import untyped.termalg.shared.TermAlgQuery;

public interface Eval1<Term> extends TermAlgQuery<Term, Term>, varapp.Eval1<Term> {
	@Override
	GTermAlg<Term, Term> alg();
	@Override
	TermShiftAndSubst<Term> termShiftAndSubst();
	@Override
	IsVal<Term> isVal();
	@Override
	TermAlgMatcher<Term, Term> matcher();
}