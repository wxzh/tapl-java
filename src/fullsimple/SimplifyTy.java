package fullsimple;

import java.util.function.Function;

import fullsimple.bindingalg.external.BindingAlgMatcher;
import fullsimple.tyalg.shared.G_TyAlgTransform;
import utils.Context;

// slightly different from the original definition: will simplify all inner TyVars
public interface SimplifyTy<Ty, Bind, Term> extends G_TyAlgTransform<Context<Bind>, Ty> {
	BindingAlgMatcher<Bind, Term, Ty, Ty> bindMatcher();

	@Override
	default Function<Context<Bind>, Ty> TyVar(int x, int n) {
		return ctx -> bindMatcher()
						.TyAbbBind(ty2 -> visitTy(ty2).apply(ctx))
						.otherwise(() -> alg().TyVar(x, n))
						.visitBind(ctx.getBinding(x));

	}
}
