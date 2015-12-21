package fullref;

import java.util.function.Function;

import fullref.termalg.shared.GTermAlg;
import fullsimple.termalg.external.TermAlgMatcher;
import utils.Context;

public interface Print<Term, Ty, Bind>
		extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, fullsimple.Print<Term, Ty, Bind> {
	@Override
	TermAlgMatcher<Term, Ty, String> matcher();
	@Override
	PrintTy<Ty, Bind> printTy();

	@Override
	default Function<Context<Bind>, String> TmDeref(Term t) {
		return ctx -> "!" + visitTerm(t).apply(ctx);
	}
	@Override
	default Function<Context<Bind>, String> TmLoc(int l) {
		return ctx -> "<loc #" + l + ">";
	}
	@Override
	default Function<Context<Bind>, String> TmAssign(Term t1, Term t2) {
		return ctx -> visitTerm(t1).apply(ctx) + " := " + visitTerm(t2).apply(ctx);
	}
	@Override
	default Function<Context<Bind>, String> TmRef(Term t) {
		return ctx -> "ref " + visitTerm(t).apply(ctx);
	}
}