package fullsimple;

import java.util.function.Function;

import fullsimple.bindingalg.shared.GBindingAlg;
import fullsimple.termalg.shared.TermAlgQuery;
import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import library.Zero;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>,
		nat.Typeof<Term, Ty, Bind>, simplebool.Typeof<Term, Ty, Bind>, record.Typeof<Term, Ty, Bind>, variant.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	GBindingAlg<Bind, Term, Ty, Bind> bindAlg();
	@Override
	GTyAlg<Ty, Ty> tyAlg();
	@Override
	GetTypeFromBind<Bind, Term, Ty> getTypeFromBind();

	@Override
	default Function<Context<Bind>, Ty> TmLet(String x, Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			return visitTerm(t2).apply(ctx.addBinding(x, bindAlg().VarBind(tyT1)));
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmTimesFloat(Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			Ty tyT2 = visitTerm(t2).apply(ctx);
			Ty tyFloat = tyAlg().TyFloat();
			return tyEqv().visitTy(tyT1).tyEqv(tyFloat) &&
					tyEqv().visitTy(tyT2).tyEqv(tyFloat)
					? tyFloat
					: m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmFloat(float p1) {
		return ctx -> tyAlg().TyFloat();
	}

	@Override
	default Function<Context<Bind>, Ty> TmString(String p1) {
		return ctx -> tyAlg().TyString();
	}

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

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		return simplebool.Typeof.super.m();
	}
}