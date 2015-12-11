package simplebool;

import java.util.function.Function;

import library.Tuple2;
import utils.Context;
import utils.PrintVarApp;

public interface Print<Term, Ty, Bind> extends simplebool.termalg.shared.TermAlg<Term, Ty, Function<Context<Bind>, String>>, PrintVarApp<Term, Bind> {
	PrintTy<Ty, Bind> printTy();
	PrintBind<Bind, Term, Ty> printBind();

	@Override
	default Function<Context<Bind>, String> TmAbs(String x, Ty ty, Term t) {
		return ctx -> {
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(x);
			return "lambda " + pr._2 + ":" + printTy().visitTy(ty) + "." + visitTerm(t).apply(pr._1);
		};
	}
}
