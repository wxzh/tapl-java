package utils;

import java.util.function.Function;

import library.Zero;
import utils.tyalg.external.TyAlgMatcher;
import utils.tyalg.shared.TyAlgQuery;

public interface TyEqv<Ty> extends TyAlgQuery<Ty, Function<Ty, Boolean>> {
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default Zero<Function<Ty, Boolean>> m() {
		return new Zero<Function<Ty, Boolean>>() {
			@Override
			public Function<Ty, Boolean> empty() {
				return ty -> false;
			}
		};
	}

	@Override
	default Function<Ty, Boolean> TyBool() {
		return ty -> matcher()
				.TyBool(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}