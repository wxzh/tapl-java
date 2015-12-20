package moreextension;

import java.util.function.Function;

import moreextension.termalg.external.TermAlgMatcher;
import moreextension.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, extension.Print<Term, Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	TermAlgMatcher<Term, Ty, String> matcher();

	@Override
	default Function<Context<Bind>, String> TmInert(Ty ty) {
		return ctx -> "inert[" + printTy().visitTy(ty).apply(ctx) + "]";
	}

	@Override
	default Function<Context<Bind>, String> TmFix(Term t) {
		return ctx -> "fix " + visitTerm(t).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmAscribe(Term t, Ty ty) {
		return ctx -> visitTerm(t).apply(ctx) + " as " + printTy().visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmUnit() {
		return ctx -> "Unit";
	}
}