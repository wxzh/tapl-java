package fulluntyped;

import java.util.function.Function;

import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>>,
		untyped.Print<Term, Bind>, extension.Print<Term, Bind> {
	@Override
	PrintBind<Bind, Term> printBind();

	@Override
	TermAlgMatcher<Term, String> matcher();
}