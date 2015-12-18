package top;

import java.util.function.Function;

import top.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, typed.PrintTy<Ty, Bind> {
	@Override
	default Function<Context<Bind>, String> TyTop() {
		return ctx -> "Top";
	}
}