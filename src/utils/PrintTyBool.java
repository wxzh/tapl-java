package utils;

import java.util.function.Function;

public interface PrintTyBool<Ty, Bind> extends utils.tyboolalg.shared.TyBoolAlg<Ty, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TyBool() {
		return ctx -> "Bool";
	}
}
