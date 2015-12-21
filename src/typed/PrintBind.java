package typed;

import typed.bindingalg.shared.BindingAlgQuery;
import utils.IPrint;

public interface PrintBind<Bind, Ty> extends BindingAlgQuery<Bind, Ty, IPrint<Bind>>, utils.PrintBind<Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	default IPrint<Bind> VarBind(Ty ty) {
		return ctx -> ": " + printTy().visitTy(ty).print(ctx);
	}
}
