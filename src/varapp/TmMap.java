package varapp;

import java.util.function.Function;

import varapp.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term> extends G_TermAlgTransform<TmMapCtx<Term>, Term> {
	default Function<TmMapCtx<Term>, Term> TmVar(int x, int n) {
		return ctx -> ctx.tmMap(x, n);
	}
}