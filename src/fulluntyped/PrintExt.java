package fulluntyped;

import java.util.function.Function;

import fulluntyped.extalg.external.ExtAlgMatcher;
import fulluntyped.extalg.shared.GExtAlg;
import utils.Context;

public interface PrintExt<Term, Bind> extends GExtAlg<Term, Function<Context<Bind>, String>>, arith.Print<Term, Bind>, record.Print<Term, Bind> {
	@Override
	ExtAlgMatcher<Term, String> matcher();

	default Function<Context<Bind>, String> TmString(String s) {
		return ctx -> s;
	}

	default Function<Context<Bind>, String> TmFloat(float f) {
		return ctx -> String.valueOf(f);
	}

	default Function<Context<Bind>, String> TmTimesFloat(Term t1, Term t2) {
		return ctx -> "timesfloat " + visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}
}