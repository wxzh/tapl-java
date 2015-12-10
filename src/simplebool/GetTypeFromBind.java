package simplebool;

import library.Zero;
import simplebool.bindingalg.shared.BindingAlgQuery;

public interface GetTypeFromBind<Bind, Ty> extends BindingAlgQuery<Bind, Ty, Ty> {
	default Zero<Ty> m() {
		return () -> { throw new RuntimeException(); };
	}

	default Ty VarBind(Ty ty) {
		return ty;
	}
}
