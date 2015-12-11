package untyped;

import java.util.function.Function;

import library.Tuple2;
import utils.Context;
import utils.PrintVarApp;

public interface Print<Term, Bind> extends untyped.termalg.shared.TermAlg<Term, Function<Context<Bind>, String>>, PrintVarApp<Term, Bind> {
	PrintBind<Bind> printBind();
	default Function<Context<Bind>, String> TmAbs(String x, Term t) {
		return ctx -> {
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(x);
			return "\\" + pr._2 + "." + visitTerm(t).apply(pr._1);
		};
	}
}
