package fullsimple;

import fullsimple.bindingalg.external.BindingAlgMatcher;
import fullsimple.tyalg.external.TyAlgMatcher;
import utils.Context;

public interface SimplifyTy<Ty, Bind, Term> {
	TyAlgMatcher<Ty, Ty> tyMatcher();

	BindingAlgMatcher<Bind, Term, Ty, Ty> bindMatcher();

	fullsimple.tyalg.shared.TyAlg<Ty, Ty> tyAlg();

	default Ty simplifyTy(Context<Bind> ctx, Ty ty) {
		return tyMatcher()
				.TyVar(x -> n -> bindMatcher()
						.TyAbbBind(ty2 -> simplifyTy(ctx, ty2))
						.otherwise(() -> tyAlg().TyVar(x, n))
						.visitBind(ctx.getBinding(x)))
				.otherwise(() -> ty).visitTy(ty);
	}
}
