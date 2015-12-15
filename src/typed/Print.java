package typed;

import java.util.function.Function;

import library.Tuple2;
import typed.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, varapp.Print<Term, Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	PrintBind<Bind, Ty> printBind();

	@Override
	default Function<Context<Bind>, String> TmAbs(String x, Ty ty, Term t) {
		return ctx -> {
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(x);
			return "lambda " + pr._2 + ":" + printTy().visitTy(ty) + "." + visitTerm(t).apply(pr._1);
		};
	}
}