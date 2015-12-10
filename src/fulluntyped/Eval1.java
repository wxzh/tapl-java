package fulluntyped;

import fulluntyped.bindingalg.external.BindingAlgMatcher;
import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;
import utils.Context;

public interface Eval1<Term, Bind> extends TermAlgQuery<Term, Term>, untyped.Eval1<Term>, arith.Eval1<Term>, Eval1Untyped<Term, Bind> {
	IsVal<Term> isVal();
	TermAlgMatcher<Term, Term> matcher();
	BindingAlgMatcher<Bind, Term, Term> bindMatcher();
	fulluntyped.termalg.shared.TermAlg<Term, Term> alg();
	Context<Bind> ctx();
	TmMap<Term> tmMap();

	@Override
	default Zero<Term> m() {
		return untyped.Eval1.super.m();
	}

	@Override
	default Term TmLet(String x, Term t1, Term t2) {
		return isVal().visitTerm(t1) ? termSubstTop(t1, t2) : alg().TmLet(x, visitTerm(t1), t2);
	}

	default Term TmVar(int x, int n) {
		return bindMatcher()
				.TmAbbBind(t -> t)
				.otherwise(() -> m().empty())
				.visitBind(ctx().getBinding(x));
	}
}
