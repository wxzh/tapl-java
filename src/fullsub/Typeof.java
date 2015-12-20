package fullsub;

import moreextension.TyEqv;
import moreextension.tyalg.external.TyAlgMatcher;
import moreextension.tyalg.shared.GTyAlg;

public interface Typeof<Term, Ty, Bind> extends moreextension.Typeof<Term, Ty, Bind>, typed.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	GTyAlg<Ty, Ty> tyAlg();
}
