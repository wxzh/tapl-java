package fullerror;

import java.util.function.Function;

import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;

public interface TyEqv<Ty> extends TyAlgQuery<Ty, Function<Ty, Boolean>>, simplebool.TyEqv<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default Function<Ty, Boolean> TyTop() {
		return ty -> matcher()
				.TyTop(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyBot() {
		return ty -> matcher()
				.TyBot(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Zero<Function<Ty, Boolean>> m() {
		return simplebool.TyEqv.super.m();
	}
}
