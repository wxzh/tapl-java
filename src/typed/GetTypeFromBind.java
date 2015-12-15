package typed;

import library.Zero;
import typed.bindingalg.shared.BindingAlgQuery;

public interface GetTypeFromBind<Bind, Ty> extends BindingAlgQuery<Bind, Ty, Ty> {
	@Override
	default Zero<Ty> m() {
		throw new RuntimeException();
	}

	@Override
	default Ty VarBind(Ty ty) {
		return ty;
	}
}
