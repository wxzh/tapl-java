package fullsimple;

import java.util.function.Function;

import fullsimple.termalg.external.TermAlgMatcher;
import fullsimple.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>,
		variant.Print<Term, Ty, Bind>, moreextension.Print<Term, Ty, Bind> {
	@Override
	TermAlgMatcher<Term, Ty, String> matcher();

	@Override
	PrintTy<Ty, Bind> printTy();

}