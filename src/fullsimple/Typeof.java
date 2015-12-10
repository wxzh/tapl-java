package fullsimple;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fullsimple.termalg.shared.TermAlgQuery;
import fullsimple.tyalg.external.TyAlgMatcher;
import library.Tuple2;
import library.Tuple3;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>,
		tyarith.Typeof<Term, Ty, Bind>, simplebool.Typeof<Term, Ty, Bind>, TypeShiftAndSubst<Ty>, SimplifyTy<Ty, Bind, Term> {

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	fullsimple.bindingalg.shared.BindingAlg<Bind, Term, Ty, Bind> bindAlg();

	@Override
	fullsimple.tyalg.shared.TyAlg<Ty, Ty> tyAlg();

	@Override
	TyEqv<Ty, Bind, Term> tyEqv();

	@Override
	default boolean tyEqv(Context<Bind> ctx, Ty ty1, Ty ty2) {
		Ty tyS1 = simplifyTy(ctx, ty1);
		Ty tyS2 = simplifyTy(ctx, ty2);
		return tyEqv().visitTy(tyS1).apply(tyS2);
	}

	@Override
	default Function<Context<Bind>, Ty> TmLet(String x, Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			return typeShift(-1, visitTerm(t2).apply(ctx.addBinding(x, bindAlg().VarBind(tyT1))));
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmTimesFloat(Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			Ty tyT2 = visitTerm(t2).apply(ctx);
			Ty tyFloat = tyAlg().TyFloat();
			return tyEqv().visitTy(tyT1).apply(tyFloat) && tyEqv().visitTy(tyT2).apply(tyFloat) ? tyFloat
					: m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmProj(Term t, String l) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyMatcher()
					.TyRecord(fieldTys -> fieldTys.stream().filter(pr -> pr._1.equals(l)).findFirst().map(pr -> pr._2)
							.orElseGet(() -> m().empty().apply(ctx)))
					.otherwise(() -> m().empty().apply(ctx)).visitTy(tyT);
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
	default Function<Context<Bind>, Ty> TmRecord(List<Tuple2<String, Term>> fields) {
		return ctx -> tyAlg().TyRecord(fields.stream().map(pr -> new Tuple2<>(pr._1, visitTerm(pr._2).apply(ctx)))
				.collect(Collectors.toList()));
	}

	@Override
	default Function<Context<Bind>, Ty> TmUnit() {
		return ctx -> tyAlg().TyUnit();
	}

	@Override
	default Function<Context<Bind>, Ty> TmTag(String l, Term t, Ty ty) {
		return ctx -> tyMatcher()
				.TyVariant(fieldTys -> fieldTys.stream().filter(pr -> pr._1.equals(l)).findFirst().map(pr -> {
					Ty tyTExpected = pr._2;
					Ty tyT = visitTerm(t).apply(ctx);
					return tyEqv(ctx, tyT, tyTExpected) ? ty : m().empty().apply(ctx);
				}).orElseGet(() -> m().empty().apply(ctx))).otherwise(() -> m().empty().apply(ctx))
				.visitTy(simplifyTy(ctx, ty));
	}

	@Override
	default Function<Context<Bind>, Ty> TmInert(Ty ty) {
		return ctx -> ty;
	}

	@Override
	default Function<Context<Bind>, Ty> TmCase(Term t, List<Tuple3<String, String, Term>> cases) {
		return ctx -> {
			Ty tyT = simplifyTy(ctx, visitTerm(t).apply(ctx));
			return tyMatcher().TyVariant(fieldsTys -> {
				if (cases.stream().allMatch(triple -> fieldsTys.stream().anyMatch(pr -> pr._1.equals(triple._1)))) {
					// all case labels are contained
					List<Ty> caseTypes = cases.stream().map(triple -> {
						String li = triple._1;
						String xi = triple._2;
						Term ti = triple._3;
						Ty tyi = fieldsTys.stream().filter(pr -> pr._1.equals(li)).findFirst().get()._2;
						return typeShift(-1, visitTerm(ti).apply(ctx.addBinding(xi, bindAlg().VarBind(tyi))));
					}).collect(Collectors.toList());
					// all case terms of the same type
					Ty tyT1 = caseTypes.get(0);
					if (caseTypes.stream().allMatch(ty -> tyEqv(ctx, ty, tyT1)))
						return tyT1;
				}
				return m().empty().apply(ctx);
			}).otherwise(() -> m().empty().apply(ctx)).visitTy(tyT);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmFix(Term t) {
		return ctx -> {
			Ty tyT = simplifyTy(ctx, visitTerm(t).apply(ctx));
			return tyMatcher().TyArr(ty1 -> ty2 -> tyEqv(ctx, ty1, ty2) ? ty2 : m().empty().apply(ctx))
					.otherwise(() -> m().empty().apply(ctx)).visitTy(tyT);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmAscribe(Term t, Ty ty) {
		return ctx -> tyEqv(ctx, visitTerm(t).apply(ctx), ty) ? ty : m().empty().apply(ctx);
	}
}