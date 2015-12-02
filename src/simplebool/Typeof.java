package simplebool;

import java.util.function.Function;

import library.Zero;
import simplebool.termalg.shared.TermAlgQuery;
import untyped.Context;
import utils.TypeError;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>> {
	simplebool.tyalg.shared.TyAlg<Ty, Ty> tyAlg();
	BindingAlg<Bind, Ty> bindAlg();
	GetTypeFromBind<Bind, Ty> getTypeFromBind();

	default Zero<Function<Context<Bind>, Ty>> m() {
		return new Zero<Function<Context<Bind>, Ty>>() {
			public Function<Context<Bind>, Ty> empty() {
				return ctx -> {
					throw new TypeError();
				};
			}
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