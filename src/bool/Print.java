package bool;

import java.util.function.Function;

import bool.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TmTrue() {
		return ctx -> "true";
	}

	default Function<Context<Bind>, String> TmFalse() {
		return ctx -> "false";
	}

	default Function<Context<Bind>, String> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> "if " + visitTerm(t1).apply(ctx) + " then " + visitTerm(t2).apply(ctx) + " else "
				+ visitTerm(t3).apply(ctx);
	}
}
