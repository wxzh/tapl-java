package arith;

import java.util.function.Function;

import arith.termalg.external.TermAlgMatcher;
import arith.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>>, bool.Print<Term, Bind>, nat.Print<Term, Bind> {
	@Override
	TermAlgMatcher<Term, String> matcher();

	default Function<Context<Bind>, String> TmIsZero(Term t) {
		return ctx -> "(iszero " + visitTerm(t).apply(ctx) + ")";
	}
}