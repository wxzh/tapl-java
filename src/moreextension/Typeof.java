package moreextension;

import java.util.function.Function;

import moreextension.termalg.shared.TermAlgQuery;
import moreextension.tyalg.external.TyAlgMatcher;
import moreextension.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>, extension.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	GTyAlg<Ty, Ty> tyAlg();

	@Override
	default Function<Context<Bind>, Ty> TmUnit() {
		return ctx -> tyAlg().TyUnit();
	}

	@Override
	default Function<Context<Bind>, Ty> TmInert(Ty ty) {
		return ctx -> ty;
	}

	@Override
	default Function<Context<Bind>, Ty> TmFix(Term t) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyMatcher()
					.TyArr(ty1 -> ty2 -> tyEqv().visitTy(ty1).tyEqv(ty2) ? ty2 : m().empty().apply(ctx))
					.otherwise(() -> m().empty().apply(ctx))
					.visitTy(tyT);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmAscribe(Term t, Ty ty) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyEqv().visitTy(tyT).tyEqv(ty) ? ty : m().empty().apply(ctx);
		};
	}
}