package fullsimple;

import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import utils.ITyEqv;

public interface TyEqv<Ty> extends GTyAlg<Ty, ITyEqv<Ty>>, simplebool.TyEqv<Ty>, nat.TyEqv<Ty>, record.TyEqv<Ty>, variant.TyEqv<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default ITyEqv<Ty> TyUnit() {
		return ty -> matcher().TyUnit(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyFloat() {
		return ty -> matcher().TyFloat(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyId(String b1) {
		return ty -> matcher().TyId(b2 -> b1 == b2).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyString() {
		return ty -> matcher().TyString(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyNat() {
		return nat.TyEqv.super.TyNat();
	}
}