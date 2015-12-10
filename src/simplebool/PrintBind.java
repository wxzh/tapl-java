package simplebool;

import java.util.function.Function;

import simplebool.bindingalg.shared.BindingAlgQuery;
import utils.Context;

public interface PrintBind<Bind, Term, Ty> extends BindingAlgQuery<Bind, Ty, Function<Context<Bind>, String>>, untyped.PrintBind<Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	default Function<Context<Bind>, String> VarBind(Ty ty) {
		return ctx -> ": " + printTy().visitTy(ty).apply(ctx);
	}
}
