package fulluntyped;

import java.util.function.Function;

import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>>,
		untyped.Print<Term, Bind>, PrintExt<Term, Bind> {
	@Override
	PrintBind<Bind, Term> printBind();

	@Override
	TermAlgMatcher<Term, String> matcher();

	@Override
	default Function<Context<Bind>, String> TmLet(String x, Term t1, Term t2) {
		return ctx -> "let " + x + "=" + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}
}