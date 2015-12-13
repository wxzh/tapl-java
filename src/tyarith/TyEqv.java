package tyarith;

import java.util.function.Function;

import tyarith.tyalg.external.TyAlgMatcher;
import tyarith.tyalg.shared.TyAlgQuery;

public interface TyEqv<Ty> extends TyAlgQuery<Ty, Function<Ty, Boolean>>, utils.TyEqv<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default Function<Ty, Boolean> TyNat() {
		return ty -> matcher()
				.TyNat(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}