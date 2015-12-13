package simplebool;

import library.Zero;
import simplebool.bindingalg.shared.BindingAlgQuery;

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
