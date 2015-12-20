package extension;

import java.util.function.Function;

import extension.termalg.shared.G_TermAlgTransform;
import varapp.TmMapCtx;

public interface TmMap<Term> extends G_TermAlgTransform<TmMapCtx<Term>, Term>, varapp.TmMap<Term> {
	@Override
	default Function<TmMapCtx<Term>,Term> TmLet(String x, Term bind, Term body) {
		return ctx -> alg().TmLet(x, visitTerm(bind).apply(ctx), visitTerm(body).apply(new TmMapCtx<>(ctx.onvar, ctx.c + 1)));
	}
}