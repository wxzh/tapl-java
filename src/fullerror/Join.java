package fullerror;

import java.util.function.Function;

import fullref.tyalg.external.TyAlgMatcher;
import fullref.tyalg.shared.TyAlgQuery;
import library.Zero;

public interface Join<Ty, Bind> extends TyAlgQuery<Ty, Function<Ty, Ty>> {
	TyAlgMatcher<Ty, Ty> matcher();

	Subtype<Ty, Bind> subtype();

	fullerror.tyalg.shared.TyAlg<Ty, Ty> alg();

	Meet<Ty, Bind> meet();

	default Zero<Function<Ty, Ty>> m() {
		return () -> ty -> alg().TyTop();
	}

	default Ty join(Ty ty1, Ty ty2) {
		if (subtype().subtype(ty1, ty2))
			return ty2;
		if (subtype().subtype(ty2, ty1))
			return ty1;
		return visitTy(ty1).apply(ty2);
	}


	@Override
	default Function<Ty, Ty> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher()
				.TyArr(tyT1 -> tyT2 -> alg().TyArr(meet().meet(tyS1, tyT1), join(tyS2, tyT2)))
				.otherwise(() -> m().empty().apply(ty))
				.visitTy(ty);
	}

	interface Meet<Ty, Bind> extends TyAlgQuery<Ty, Function<Ty, Ty>> {

		TyAlgMatcher<Ty, Ty> matcher();

		Subtype<Ty, Bind> subtype();

		fullerror.tyalg.shared.TyAlg<Ty, Ty> alg();

		Join<Ty, Bind> join();

		default Zero<Function<Ty, Ty>> m() {
			return () -> ty -> alg().TyBot();
		}

		default Ty meet(Ty ty1, Ty ty2) {
			if (subtype().subtype(ty1, ty2))
				return ty1;
			if (subtype().subtype(ty2, ty1))
				return ty2;
			return visitTy(ty1).apply(ty2);
		}

		@Override
		default Function<Ty, Ty> TyArr(Ty tyS1, Ty tyS2) {
			return ty -> matcher()
					.TyArr(tyT1 -> tyT2 -> alg().TyArr(join().join(tyS1, tyT1), meet(tyS2, tyT2)))
					.otherwise(() -> m().empty().apply(ty))
					.visitTy(ty);
		}
	}
}