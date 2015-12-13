package simplebool;

import java.util.function.Function;

import simplebool.termalg.shared.TermAlgQuery;
import simplebool.tyalg.external.TyAlgMatcher;
import utils.Context;

public interface Typeof<Term, Ty, Bind>
		extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>, utils.Typeof<Term, Ty, Bind> {
	@Override
	simplebool.tyalg.shared.TyAlg<Ty, Ty> tyAlg();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	TyEqv<Ty> tyEqv();

	simplebool.bindingalg.shared.BindingAlg<Bind, Ty, Bind> bindAlg();

	GetTypeFromBind<Bind, Ty> getTypeFromBind();

	@Override
	default Function<Context<Bind>, Ty> TmApp(Term t1, Term t2) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			Ty ty2 = visitTerm(t2).apply(ctx);
			return tyMatcher()
					.TyArr(ty11 -> ty12 -> tyEqv().visitTy(ty2).apply(ty11) ? ty12 : m().empty().apply(ctx))
					.otherwise(() -> m().empty().apply(ctx))
					.visitTy(ty1);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmVar(int x, int n) {
		return ctx -> getTypeFromBind().visitBind(ctx.getBinding(x));
	}

	@Override
	default Function<Context<Bind>, Ty> TmAbs(String x, Ty ty, Term t) {
		return ctx -> {
			Context<Bind> ctx2 = ctx.addBinding(x, bindAlg().VarBind(ty));
			Ty ty2 = visitTerm(t).apply(ctx2);
			return tyAlg().TyArr(ty, ty2);
		};
	}
}