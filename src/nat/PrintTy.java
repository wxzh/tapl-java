package nat;

import java.util.function.Function;

import tyarith.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, bool.PrintTy<Ty, Bind> {
	default Function<Context<Bind>, String> TyNat() {
		return ctx -> "Nat";
	}
}