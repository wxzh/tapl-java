package tyarith;

import java.util.function.Function;

import library.Zero;
import tyarith.tyalg.external.TyAlgMatcher;
import tyarith.tyalg.shared.TyAlgQuery;

public interface TyEqv<Ty> extends TyAlgQuery<Ty, Function<Ty, Boolean>>, utils.TyEqv<Ty> {
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
	default Function<Ty, Boolean> TyNat() {
		return ty -> matcher()
				.TyNat(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}