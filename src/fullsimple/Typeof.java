package fullsimple;

import java.util.function.Function;

import fullsimple.bindingalg.shared.GBindingAlg;
import fullsimple.termalg.shared.TermAlgQuery;
import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import library.Zero;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>, moreextension.Typeof<Term, Ty, Bind>, variant.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	GBindingAlg<Bind, Term, Ty, Bind> bindAlg();
	@Override
	GTyAlg<Ty, Ty> tyAlg();
	@Override
	GetTypeFromBind<Bind, Term, Ty> getTypeFromBind();

	@Override
	default Zero<Function<Context<Bind>, Ty>> m() {
		return variant.Typeof.super.m();
	}
}