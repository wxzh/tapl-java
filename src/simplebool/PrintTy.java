package simplebool;

import java.util.function.Function;

import simplebool.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, typed.PrintTy<Ty, Bind>, bool.PrintTy<Ty, Bind> {
	default Function<Context<Bind>, String> TyArr(Ty t1, Ty t2) {
		return ctx -> "(" + visitTy(t1).apply(ctx) + " -> " + visitTy(t2).apply(ctx) + ")";
	}
}