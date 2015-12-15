package typed;

import java.util.function.Function;

import typed.bindingalg.shared.BindingAlgQuery;
import utils.Context;

public interface PrintBind<Bind, Ty> extends BindingAlgQuery<Bind, Ty, Function<Context<Bind>, String>>, utils.PrintBind<Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	default Function<Context<Bind>, String> VarBind(Ty ty) {
		return ctx -> ": " + printTy().visitTy(ty).apply(ctx);
	}
}
