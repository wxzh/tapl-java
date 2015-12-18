package fulluntyped;

import fulluntyped.bindingalg.external.BindingAlgMatcher;
import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.shared.GTermAlg;
import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;
import utils.Context;

public interface Eval1<Term, Bind> extends TermAlgQuery<Term, Term>, untyped.Eval1<Term>, extension.Eval1<Term, Bind> {
	@Override
	IsVal<Term> isVal();
	@Override
	TermAlgMatcher<Term, Term> matcher();
	BindingAlgMatcher<Bind, Term, Term> bindMatcher();
	@Override
	GTermAlg<Term, Term> alg();
	@Override
	TermShiftAndSubst<Term> termShiftAndSubst();

	Context<Bind> ctx();

	@Override
	default Zero<Term> m() {
		return untyped.Eval1.super.m();
	}

	@Override
	default Term TmLet(String x, Term t1, Term t2) {
		return isVal().visitTerm(t1) ? termShiftAndSubst().termSubstTop(t1, t2) : alg().TmLet(x, visitTerm(t1), t2);
	}

	default Term TmVar(int x, int n) {
		return bindMatcher()
				.TmAbbBind(t -> t)
				.otherwise(() -> m().empty())
				.visitBind(ctx().getBinding(x));
	}
}
