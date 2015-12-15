package simplebool;

import java.util.function.Function;

import simplebool.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, typed.Print<Term, Ty, Bind>, bool.Print<Term, Bind> {
	@Override
	PrintTy<Ty, Bind> printTy();
}