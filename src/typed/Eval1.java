package typed;

import library.Zero;
import typed.termalg.external.TermAlgMatcher;
import typed.termalg.shared.GTermAlg;
import typed.termalg.shared.TermAlgQuery;
import utils.NoRuleApplies;

public interface Eval1<Term, Ty> extends TermAlgQuery<Term, Ty, Term> {
	TermShiftAndSubst<Term, Ty> termShiftAndSubst();
	IsVal<Term, Ty> isVal();
	GTermAlg<Term, Ty, Term> alg();
	TermAlgMatcher<Term, Ty, Term> matcher();

	@Override
	default Zero<Term> m() {
		throw new NoRuleApplies() ;
	}

	default Term TmApp(Term t1, Term t2) {
		return matcher()
				.TmAbs(x -> ty -> t -> isVal().visitTerm(t2) ? termShiftAndSubst().termSubstTop(t2, t)
						: (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(() -> alg().TmApp(visitTerm(t1), t2)).visitTerm(t1);
	}
}
