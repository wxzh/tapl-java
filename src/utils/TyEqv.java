package utils;

import java.util.function.Function;

import library.Zero;
import utils.tyboolalg.external.TyBoolAlgMatcher;
import utils.tyboolalg.shared.TyBoolAlgQuery;

public interface TyEqv<Ty> extends TyBoolAlgQuery<Ty, Function<Ty, Boolean>> {
	TyBoolAlgMatcher<Ty, Boolean> matcher();

	@Override
	default Zero<Function<Ty, Boolean>> m() {
		return () -> ty -> false;
	}

	@Override
	default Function<Ty, Boolean> TyBool() {
		return ty -> matcher()
				.TyBool(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}