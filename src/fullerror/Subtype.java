package fullerror;

import java.util.function.Function;

import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;

public interface Subtype<Ty, Bind> extends TyAlgQuery<Ty, Function<Ty, Boolean>> {
	TyAlgMatcher<Ty, Boolean> matcher();
	TyEqv<Ty> tyEqv();
	fullerror.tyalg.shared.TyAlg<Ty, Ty> alg();

	@Override
	default Zero<Function<Ty, Boolean>> m() {
		return () -> ty -> false;
	}

	default boolean subtype(Ty ty1, Ty ty2) {
		return tyEqv().visitTy(ty1).apply(ty2) || visitTy(ty1).apply(ty2)
				|| matcher().TyTop(() -> true).otherwise(() -> false).visitTy(ty2);
	}

	@Override
	default Function<Ty, Boolean> TyBot() {
		return ty -> true;
	}

	@Override
	default Function<Ty, Boolean> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher()
				.TyArr(tyT1 -> tyT2 -> subtype(tyS1, tyT1) && subtype(tyS2, tyT2))
				.otherwise(() -> false)
				.visitTy(ty);
	}
}