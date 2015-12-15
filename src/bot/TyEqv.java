package bot;

import bot.tyalg.external.TyAlgMatcher;
import bot.tyalg.shared.GTyAlg;
import utils.ITyEqv;

public interface TyEqv<Ty> extends GTyAlg<Ty, ITyEqv<Ty>> {
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default ITyEqv<Ty> TyTop() {
		return ty -> matcher()
				.TyTop(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyBot() {
		return ty -> matcher()
				.TyBot(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}