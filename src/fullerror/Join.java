package fullerror;

import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.GTyAlg;
import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;

interface IJoin<Ty> {
	Ty join(Ty ty);
}

interface IMeet<Ty> {
	Ty meet(Ty ty);
}

// page: 218
public interface Join<Ty, Bind> extends TyAlgQuery<Ty, IJoin<Ty>> {
	TyAlgMatcher<Ty, Ty> matcher();
	Subtype<Ty> subtype();
	GTyAlg<Ty, Ty> alg();
	Meet<Ty, Bind> meet();

	default Zero<IJoin<Ty>> m() {
		return () -> ty -> alg().TyTop();
	}

	default Ty join(Ty ty1, Ty ty2) {
		if (subtype().subtype(ty1, ty2))
			return ty2;
		if (subtype().subtype(ty2, ty1))
			return ty1;
		return visitTy(ty1).join(ty2);
	}

	@Override
	default IJoin<Ty> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher()
				.TyArr(tyT1 -> tyT2 -> alg().TyArr(meet().meet(tyS1, tyT1), join(tyS2, tyT2)))
				.otherwise(() -> m().empty().join(ty))
				.visitTy(ty);
	}

	interface Meet<Ty, Bind> extends TyAlgQuery<Ty, IMeet<Ty>> {
		TyAlgMatcher<Ty, Ty> matcher();
		Subtype<Ty> subtype();
		GTyAlg<Ty, Ty> alg();
		Join<Ty, Bind> join();

		default Zero<IMeet<Ty>> m() {
			return () -> ty -> alg().TyBot();
		}

		default Ty meet(Ty ty1, Ty ty2) {
			if (subtype().subtype(ty1, ty2))
				return ty1;
			if (subtype().subtype(ty2, ty1))
				return ty2;
			return visitTy(ty1).meet(ty2);
		}

		@Override
		default IMeet<Ty> TyArr(Ty tyS1, Ty tyS2) {
			return ty -> matcher()
					.TyArr(tyT1 -> tyT2 -> alg().TyArr(join().join(tyS1, tyT1), meet(tyS2, tyT2)))
					.otherwise(() -> m().empty().meet(ty))
					.visitTy(ty);
		}
	}
}