package top;

import java.util.function.Function;

import typed.termalg.shared.GTermAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, Ty>>, typed.Typeof<Term, Ty, Bind> {
	Subtype<Ty> subtype();

	@Override
	default Function<Context<Bind>, Ty> TmApp(Term t1, Term t2) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			Ty ty2 = visitTerm(t2).apply(ctx);
			return tyMatcher()
					.TyArr(ty11 -> ty12 -> subtype().subtype(ty2, ty11) ? ty12 : m().empty().apply(ctx))
					.otherwise(() -> m().empty().apply(ctx))
					.visitTy(ty1);
		};
	}
}
