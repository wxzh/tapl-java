package untyped;

import java.util.function.Function;

import library.Tuple2;
import untyped.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>>, varapp.Print<Term, Bind> {
	default Function<Context<Bind>, String> TmAbs(String x, Term t) {
		return ctx -> {
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(x);
			return "\\" + pr._2 + "." + visitTerm(t).apply(pr._1);
		};
	}
}
