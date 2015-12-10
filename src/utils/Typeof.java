package utils;

import java.util.function.Function;

import arith.boolalg.shared.BoolAlgQuery;
import library.Zero;
import utils.tyalg.external.TyAlgMatcher;

public interface Typeof<Term, Ty, Bind> extends BoolAlgQuery<Term, Function<Context<Bind>, Ty>> {
	utils.tyalg.shared.TyAlg<Ty, Ty> tyAlg();
	TyAlgMatcher<Ty, Ty> tyMatcher();
	utils.TyEqv<Ty> tyEqv();

	default boolean tyEqv(Context<Bind> ctx, Ty ty1, Ty ty2) {
		return tyEqv().visitTy(ty1).apply(ty2);
	}

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		return () -> ctx -> { throw new TypeError(); };
	}

	@Override
	default Function<Context<Bind>, Ty> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			if (tyEqv(ctx, ty1, tyAlg().TyBool())) {
				Ty ty2 = visitTerm(t2).apply(ctx);
				Ty ty3 = visitTerm(t3).apply(ctx);
				if (tyEqv(ctx, ty2, ty3))
					return ty2;
			}
			return m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmTrue() {
		return ctx -> tyAlg().TyBool();
	}

	@Override
	default Function<Context<Bind>, Ty> TmFalse() {
		return ctx -> tyAlg().TyBool();
	}
}
