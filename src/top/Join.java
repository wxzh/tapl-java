package top;

import library.Zero;
import top.tyalg.shared.TyAlgQuery;
import utils.IJoin;

public interface Join<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, IJoin<Ty>> {
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