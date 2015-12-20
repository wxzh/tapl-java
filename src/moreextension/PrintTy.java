package moreextension;

import java.util.function.Function;

import moreextension.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, extension.PrintTy<Ty, Bind> {
	default Function<Context<Bind>, String> TyUnit() {
		return ctx -> "Unit";
	}
}