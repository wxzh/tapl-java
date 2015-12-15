package simplebool;

import java.util.function.Function;

import simplebool.termalg.shared.GTermAlg;
import simplebool.tyalg.external.TyAlgMatcher;
import simplebool.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind>
		extends GTermAlg<Term, Ty, Function<Context<Bind>, Ty>>, typed.Typeof<Term, Ty, Bind>, bool.Typeof<Term, Ty, Bind> {
	@Override
	GTyAlg<Ty, Ty> tyAlg();

	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	TyEqv<Ty> tyEqv();
}