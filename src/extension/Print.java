package extension;

import java.util.function.Function;

import extension.termalg.external.TermAlgMatcher;
import extension.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>>, varapp.Print<Term, Bind>,
		arith.Print<Term, Bind>, record.Print<Term, Bind> {
	@Override
	TermAlgMatcher<Term, String> matcher();

	@Override
	default Function<Context<Bind>, String> TmString(String s) {
		return ctx -> s;
	}

	@Override
	default Function<Context<Bind>, String> TmFloat(float f) {
		return ctx -> String.valueOf(f);
	}

	@Override
	default Function<Context<Bind>, String> TmTimesFloat(Term t1, Term t2) {
		return ctx -> "timesfloat " + visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmLet(String x, Term t1, Term t2) {
		return ctx -> "let " + x + "=" + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}
}