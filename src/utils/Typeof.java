package utils;

import java.util.function.Function;

import arith.boolalg.shared.BoolAlgQuery;
import library.Zero;
import utils.tyboolalg.external.TyBoolAlgMatcher;

public interface Typeof<Term, Ty, Bind> extends BoolAlgQuery<Term, Function<Context<Bind>, Ty>> {
	utils.tyboolalg.shared.TyBoolAlg<Ty, Ty> tyAlg();
	TyBoolAlgMatcher<Ty, Ty> tyMatcher();
	TyEqv<Ty> tyEqv();

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		throw new TypeError();
	}

	@Override
	default Function<Context<Bind>, Ty> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			if (tyEqv().visitTy(ty1).apply(tyAlg().TyBool())) {
				Ty ty2 = visitTerm(t2).apply(ctx);
				Ty ty3 = visitTerm(t3).apply(ctx);
				if (tyEqv().visitTy(ty2).apply(ty3))
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
