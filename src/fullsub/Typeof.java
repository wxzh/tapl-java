package fullsub;

import java.util.function.Function;

import fullsub.termalg.shared.GTermAlg;
import fullsub.tyalg.external.TyAlgMatcher;
import fullsub.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, Ty>>, moreextension.Typeof<Term, Ty, Bind>, top.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	GTyAlg<Ty, Ty> tyAlg();

	Subtype<Ty> subtype();
	JoinMeet<Ty> joinMeet();

	@Override
	default Function<Context<Bind>, Ty> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			if (subtype().subtype(ty1, tyAlg().TyBool())) {
				Ty ty2 = visitTerm(t2).apply(ctx);
				Ty ty3 = visitTerm(t3).apply(ctx);
				return joinMeet().join(ty2, ty3);
			}
			return m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmFix(Term t) {
		return ctx -> {
			Ty ty = visitTerm(t).apply(ctx);
			return tyMatcher()
					.TyArr(ty1 -> ty2 -> subtype().subtype(ty1, ty2) ? ty2 : m().empty().apply(ctx))
					.otherwise(() -> m().empty().apply(ctx))
					.visitTy(ty);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmTimesFloat(Term t1, Term t2) {
		Ty tyFloat = tyAlg().TyFloat();
		return ctx -> subtype().subtype(visitTerm(t1).apply(ctx), tyFloat) &&
				subtype().subtype(visitTerm(t2).apply(ctx), tyFloat) ? tyFloat : m().empty().apply(ctx);
	}

	@Override
	default Function<Context<Bind>, Ty> TmAscribe(Term t, Ty ty) {
		return ctx -> subtype().subtype(visitTerm(t).apply(ctx), ty) ? ty : m().empty().apply(ctx);
	}

	@Override
	default Function<Context<Bind>, Ty> TmSucc(Term t) {
		Ty tyNat = tyAlg().TyNat();
		return ctx -> subtype().subtype(visitTerm(t).apply(ctx), tyNat) ? tyNat : m().empty().apply(ctx);
	}

	@Override
	default Function<Context<Bind>, Ty> TmPred(Term t) {
		Ty tyNat = tyAlg().TyNat();
		return ctx -> subtype().subtype(visitTerm(t).apply(ctx), tyNat) ? tyNat : m().empty().apply(ctx);
	}

	@Override
	default Function<Context<Bind>, Ty> TmIsZero(Term t) {
		return ctx -> subtype().subtype(visitTerm(t).apply(ctx), tyAlg().TyNat()) ? tyAlg().TyBool() : m().empty().apply(ctx);
	}
}