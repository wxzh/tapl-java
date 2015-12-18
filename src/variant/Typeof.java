package variant;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import library.Tuple3;
import library.Zero;
import utils.Context;
import utils.TypeError;
import variant.termalg.shared.TermAlgQuery;
import variant.tyalg.external.TyAlgMatcher;
import variant.tyalg.shared.GTyAlg;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>, typed.Typeof<Term, Ty, Bind> {
	@Override
	GTyAlg<Ty, Ty> tyAlg();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	TyEqv<Ty> tyEqv();

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		throw new TypeError();
	}

	@Override
	default Function<Context<Bind>, Ty> TmTag(String l, Term t, Ty ty) {
		return ctx -> tyMatcher()
				.TyVariant(fieldTys -> fieldTys.stream().filter(pr -> pr._1.equals(l)).findFirst().map(pr -> {
					Ty tyTExpected = pr._2;
					Ty tyT = visitTerm(t).apply(ctx);
					return tyEqv().visitTy(tyT).tyEqv(tyTExpected) ? ty : m().empty().apply(ctx);
				}).orElseGet(() -> m().empty().apply(ctx))).otherwise(() -> m().empty().apply(ctx))
				.visitTy(ty);
	}

	@Override
	default Function<Context<Bind>, Ty> TmCase(Term t, List<Tuple3<String, String, Term>> cases) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyMatcher().TyVariant(fieldsTys -> {
				if (cases.stream().allMatch(triple -> fieldsTys.stream().anyMatch(pr -> pr._1.equals(triple._1)))) {
					// all case labels are contained
					List<Ty> caseTypes = cases.stream().map(triple -> {
						String li = triple._1;
						String xi = triple._2;
						Term ti = triple._3;
						Ty tyi = fieldsTys.stream().filter(pr -> pr._1.equals(li)).findFirst().get()._2;
						return visitTerm(ti).apply(ctx.addBinding(xi, bindAlg().VarBind(tyi)));
					}).collect(Collectors.toList());
					// all case terms of the same type
					Ty tyT1 = caseTypes.get(0);
					if (caseTypes.stream().allMatch(ty -> tyEqv().visitTy(ty).tyEqv(tyT1)))
						return tyT1;
				}
				return m().empty().apply(ctx);
			}).otherwise(() -> m().empty().apply(ctx)).visitTy(tyT);
		};
	}

}
