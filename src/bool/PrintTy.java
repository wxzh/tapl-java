package bool;

import java.util.function.Function;

import bool.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TyBool() {
		return ctx -> "Bool";
	}
}
