package fullsimple;

import fullsimple.termalg.external.TermAlgMatcher;
import fullsimple.termalg.shared.GTermAlg;
import fullsimple.termalg.shared.TermAlgQuery;
import utils.Context;

public interface Eval1<Term, Ty, Bind>
		extends TermAlgQuery<Term, Ty, Term>, moreextension.Eval1<Term, Ty, Bind>, variant.Eval1<Term, Ty> {
	Context<Bind> ctx();

	@Override
	IsVal<Term, Ty> isVal();

	@Override
	GTermAlg<Term, Ty, Term> alg();

	@Override
	TermAlgMatcher<Term, Ty, Term> matcher();

	@Override
	TermShiftAndSubst<Term, Ty> termShiftAndSubst();
}