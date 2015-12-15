package bot;

import bot.tyalg.external.TyAlgMatcher;
import bot.tyalg.shared.GTyAlg;
import bot.tyalg.shared.TyAlgQuery;
import library.Zero;
import utils.ISubtype;

public interface Subtype<Ty> extends TyAlgQuery<Ty, ISubtype<Ty>> {
	TyAlgMatcher<Ty, Boolean> matcher();
	TyEqv<Ty> tyEqv();
	GTyAlg<Ty, Ty> alg();

	default boolean subtype(Ty ty1, Ty ty2) {
		return tyEqv().visitTy(ty1).tyEqv(ty2) || visitTy(ty1).isSubtypeOf(ty2)
				|| matcher() .TyTop(() -> true).otherwise(() -> false).visitTy(ty2);
	}

	@Override
	default ISubtype<Ty> TyBot() {
		return ty -> true;
	}

	@Override
	default ISubtype<Ty> TyArr(Ty tyS1, Ty tyS2) {
		return ty -> matcher()
				.TyArr(tyT1 -> tyT2 -> subtype(tyT1, tyS1) && subtype(tyS2, tyT2))
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Zero<ISubtype<Ty>> m() {
		return () -> ty -> false;
	}
}