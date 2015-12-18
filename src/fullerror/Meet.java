package fullerror;

import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;

public interface Meet<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, Meet.IMeet<Ty>> {
	interface IMeet<Ty> {
		Ty meet(Ty ty);
	}

	default Zero<IMeet<Ty>> m() {
		return () -> ty -> alg().TyBot();
	}

	@Override
	default IMeet<Ty> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher().TyArr(tyT1 -> tyT2 -> alg().TyArr(join(tyS1, tyT1), meet(tyS2, tyT2)))
				.otherwise(() -> m().empty().meet(ty)).visitTy(ty);
	}
}