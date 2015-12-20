package typed;

import library.Zero;
import typed.tyalg.shared.TyAlgQuery;
import utils.IMeet;

public interface Meet<Ty> extends TyAlgQuery<Ty, IMeet<Ty>> {
	default Zero<IMeet<Ty>> m() {
		throw new RuntimeException();
	}
}
