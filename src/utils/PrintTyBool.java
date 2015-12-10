package utils;

import java.util.function.Function;

public interface PrintTyBool<Ty, Bind> extends utils.tyalg.shared.TyAlg<Ty, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TyBool() {
		return ctx -> "Bool";
	}
}
