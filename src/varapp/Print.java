package varapp;

import java.util.function.Function;

import utils.Context;
import utils.PrintBind;
import varapp.termalg.shared.GTermAlg;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>> {
	PrintBind<Bind> printBind();

	@Override
	default Function<Context<Bind>, String> TmApp(Term t1, Term t2) {
		return ctx -> visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmVar(int x, int n) {
		return ctx -> {
			if (ctx.length() == n)
				return ctx.index2Name(x);
			else
				return "[bad index: " + x + "/" + n + " in " + ctx.toString(bind -> printBind().visitBind(bind).apply(ctx)) + "]";
		};
	}
}