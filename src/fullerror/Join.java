package fullerror;

import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;

// page: 218
public interface Join<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, Join.IJoin<Ty>> {
	interface IJoin<Ty> {
		Ty join(Ty ty);
	}

	@Override
	default Zero<IJoin<Ty>> m() {
		return () -> ty -> alg().TyTop();
	}

	@Override
	default IJoin<Ty> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher()
				.TyArr(tyT1 -> tyT2 -> alg().TyArr(meet(tyS1, tyT1), join(tyS2, tyT2)))
				.otherwise(() -> m().empty().join(ty)).visitTy(ty);
	}

}