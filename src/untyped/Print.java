package untyped;

import java.util.function.Function;

import library.Tuple2;
import utils.Context;

public interface Print<Term> extends untyped.termalg.shared.TermAlg<Term, Function<Context, String>> {
	default Function<Context, String> TmAbs(String x, Term t) {
		return ctx -> {
			Tuple2<Context, String> pr = ctx.pickFreshName(x);
			return "\\" + pr._2 + "." + visitTerm(t).apply(pr._1);
		};
	}

	default Function<Context, String> TmApp(Term t1, Term t2) {
		return ctx -> visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	default Function<Context, String> TmVar(int x, int n) {
		return ctx -> {
			if (ctx.length() == n)
				return ctx.index2Name(x);
			else
				return "[bad index: " + x + "/" + n + " in " + ctx + "]";
		};
	}
}
