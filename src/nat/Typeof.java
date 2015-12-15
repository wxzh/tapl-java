package nat;

import java.util.function.Function;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import nat.tyalg.external.TyAlgMatcher;
import nat.tyalg.shared.GTyAlg;
import utils.Context;
import utils.TypeError;

public interface Typeof<Term, Ty, Bind>
		extends TermAlgQuery<Term, Function<Context<Bind>, Ty>> {
	GTyAlg<Ty, Ty> tyAlg();
	TyAlgMatcher<Ty, Ty> tyMatcher();
	TyEqv<Ty> tyEqv();

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		throw new TypeError();
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
			return tyEqv().visitTy(tyT).tyEqv(tyNat) ? tyNat : m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmPred(Term t) {
		return TmSucc(t);
	}
}