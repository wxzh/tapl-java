package fullsimple;

import java.util.function.Function;

import fullsimple.termalg.shared.G_TermAlgTransform;
import varapp.TmMapCtx;

public interface TmMap<Term, Ty> extends G_TermAlgTransform<TmMapCtx<Term>, Term, Ty>, variant.TmMap<Term, Ty> {
	default Function<TmMapCtx<Term>,Term> TmLet(String x, Term bind, Term body) {
		return ctx -> alg().TmLet(x, visitTerm(bind).apply(ctx), visitTerm(body).apply(new TmMapCtx<>(ctx.onvar, ctx.c + 1)));
	}
}