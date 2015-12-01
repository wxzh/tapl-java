package untyped;

import java.util.function.Function;

import untyped.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term> extends G_TermAlgTransform<TmMapCtx<Term>, Term> {
	default Function<TmMapCtx<Term>, Term> TmVar(int x, int n) {
		return ctx -> ctx.mapVar(x, n);
	}

	@Override
	default Function<TmMapCtx<Term>, Term> TmAbs(String x, Term t) {
		return ctx -> visitTerm(t).apply(ctx.setC(ctx.c + 1));
	}
}