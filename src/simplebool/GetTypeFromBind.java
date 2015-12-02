package simplebool;

import library.Zero;
import simplebool.bindingalg.shared.BindingAlgQuery;
import utils.ZeroRuntimException;

public interface GetTypeFromBind<Bind, Ty> extends BindingAlgQuery<Bind, Ty, Ty> {
	default Zero<Ty> m() {
		return new ZeroRuntimException<>();
	}

	default Ty VarBind(Ty ty) {
		return ty;
	}
}
