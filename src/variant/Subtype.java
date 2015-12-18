package variant;

import record.tyalg.external.TyAlgMatcher;
import record.tyalg.shared.GTyAlg;
import utils.ISubtype;

public interface Subtype<Ty> extends GTyAlg<Ty, ISubtype<Ty>> {
	TyAlgMatcher<Ty, Boolean> matcher();
	TyEqv<Ty> tyEqv();
	GTyAlg<Ty, Ty> alg();

	default boolean subtype(Ty ty1, Ty ty2) {
		return tyEqv().visitTy(ty1).tyEqv(ty2) || visitTy(ty1).isSubtypeOf(ty2);
	}
}