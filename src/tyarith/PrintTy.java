package tyarith;

import java.util.function.Function;

import utils.Context;

public interface PrintTy<Ty, Bind> extends tyarith.tyalg.shared.TyAlg<Ty, Function<Context<Bind>, String>>, utils.PrintTyBool<Ty, Bind> {
	default Function<Context<Bind>, String> TyNat() {
		return ctx -> "Nat";
	}
}