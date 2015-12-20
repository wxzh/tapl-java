package typed;

import library.Zero;
import record.tyalg.shared.TyAlgQuery;
import utils.IJoin;

public interface Join<Ty> extends TyAlgQuery<Ty, IJoin<Ty>> {
	@Override
	default Zero<IJoin<Ty>> m() {
		throw new RuntimeException();
	}
}
