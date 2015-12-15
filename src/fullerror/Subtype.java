package fullerror;

import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.GTyAlg;
import fullerror.tyalg.shared.TyAlgQuery;
import library.Zero;
import utils.ISubtype;

public interface Subtype<Ty> extends TyAlgQuery<Ty, ISubtype<Ty>>, bot.Subtype<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();
	@Override
	TyEqv<Ty> tyEqv();
	@Override
	GTyAlg<Ty, Ty> alg();

	@Override
	default Zero<ISubtype<Ty>> m() {
		return bot.Subtype.super.m();
	}
}