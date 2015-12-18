package fullerror;

import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.GTyAlg;

public interface JoinMeet<Ty> {
	Subtype<Ty> subtype();
	TyAlgMatcher<Ty, Ty> matcher();
	GTyAlg<Ty, Ty> alg();
	Join<Ty> join();
	Meet<Ty> meet();

	default Ty join(Ty ty1, Ty ty2) {
		if (subtype().subtype(ty1, ty2))
			return ty2;
		if (subtype().subtype(ty2, ty1))
			return ty1;
		return join().visitTy(ty1).join(ty2);
	}

	// meet is should not be exposed to users, but this can not be done by Java8
	default Ty meet(Ty ty1, Ty ty2) {
		if (subtype().subtype(ty1, ty2))
			return ty1;
		if (subtype().subtype(ty2, ty1))
			return ty2;
		return meet().visitTy(ty1).meet(ty2);
	}
}
