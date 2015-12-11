package utils;

import java.util.function.Function;

public interface PrintVarApp<Term, Bind> extends utils.varalg.shared.VarAlg<Term, Function<Context<Bind>, String>> {
	untyped.PrintBind<Bind> printBind();

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