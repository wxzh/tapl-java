package tyarith;

import java.util.function.Function;

import arith.termalg.shared.GTermAlg;
import library.Zero;
import tyarith.tyalg.external.TyAlgMatcher;
import tyarith.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind>
		extends GTermAlg<Term, Function<Context<Bind>, Ty>>, bool.Typeof<Term, Ty, Bind>, nat.Typeof<Term, Ty, Bind> {
	@Override
	GTyAlg<Ty, Ty> tyAlg();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	TyEqv<Ty> tyEqv();

	@Override
	default Function<Context<Bind>, Ty> TmIsZero(Term t) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyEqv().visitTy(tyT).tyEqv(tyAlg().TyNat()) ? tyAlg().TyBool() : m().empty().apply(ctx);
		};
	}

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		return nat.Typeof.super.m();
	}
}