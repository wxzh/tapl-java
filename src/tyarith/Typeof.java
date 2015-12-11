package tyarith;

import java.util.function.Function;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import tyarith.tyalg.external.TyAlgMatcher;
import utils.Context;

public interface Typeof<Term, Ty, Bind>
		extends TermAlgQuery<Term, Function<Context<Bind>, Ty>>, utils.Typeof<Term, Ty, Bind> {
	@Override
	tyarith.tyalg.shared.TyAlg<Ty, Ty> tyAlg();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	default Function<Context<Bind>, Ty> TmIsZero(Term t) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyEqv(ctx, tyT, tyAlg().TyNat()) ? tyAlg().TyBool() : m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmZero() {
		return ctx -> tyAlg().TyNat();
	}

	@Override
	default Function<Context<Bind>, Ty> TmSucc(Term t) {
		return ctx -> {
			Ty tyNat = tyAlg().TyNat();
			Ty tyT = visitTerm(t).apply(ctx);
			return tyEqv(ctx, tyT, tyNat) ? tyNat : m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmPred(Term t) {
		return TmSucc(t);
	}

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		return utils.Typeof.super.m();
	}
}