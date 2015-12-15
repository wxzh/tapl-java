package varapp;

import library.Zero;
import untyped.termalg.external.TermAlgMatcher;
import untyped.termalg.shared.TermAlgQuery;
import utils.NoRuleApplies;
import varapp.termalg.shared.GTermAlg;

public interface Eval1<Term> extends TermAlgQuery<Term, Term>  {
	GTermAlg<Term, Term> alg();
	TermShiftAndSubst<Term> termShiftAndSubst();
	IsVal<Term> isVal();
	TermAlgMatcher<Term, Term> matcher();

	@Override
	default Zero<Term> m() {
		throw new NoRuleApplies();
	}

	@Override
	default Term TmApp(Term t1, Term t2) {
		return matcher()
				.TmAbs(x -> t -> isVal().visitTerm(t2) ? termShiftAndSubst().termSubstTop(t2, t)
						: (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(
						() -> isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2))
				.visitTerm(t1);
	}
}