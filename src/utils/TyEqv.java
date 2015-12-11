package utils;

import java.util.function.Function;

import library.Zero;
import utils.tyalg.external.TyAlgMatcher;
import utils.tyalg.shared.TyAlgQuery;

public interface TyEqv<Ty, Bind> {
	TyEqual<Ty> tyEqual();

	interface TyEqual<Ty> extends TyAlgQuery<Ty, Function<Ty, Boolean>> {
		TyAlgMatcher<Ty, Boolean> matcher();

		@Override
		default Zero<Function<Ty, Boolean>> m() {
			return () -> ty -> false;
		}

		@Override
		default Function<Ty, Boolean> TyBool() {
			return ty -> matcher().TyBool(() -> true).otherwise(() -> false).visitTy(ty);
		}
	}

	default boolean tyEqv(Context<Bind> ctx, Ty ty1, Ty ty2) {
		return tyEqual().visitTy(ty1).apply(ty2);
	}
}