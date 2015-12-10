package simplebool;

import java.util.function.Function;

import tyarith.tyalg.external.TyAlgMatcher;
import utils.Context;

public interface PrintTy<Ty, Bind> extends simplebool.tyalg.shared.TyAlg<Ty, Function<Context<Bind>, String>>, utils.PrintTyBool<Ty, Bind> {
	TyAlgMatcher<Ty, String> matcher();

	default Function<Context<Bind>, String> TyArr(Ty t1, Ty t2) {
		return ctx -> "(" + visitTy(t1).apply(ctx) + " -> " + visitTy(t2).apply(ctx) + ")";
	}
}