package simplebool;

import java.util.function.Function;

import library.Tuple2;
import utils.Context;

public interface Print<Term, Ty, Bind> extends simplebool.termalg.shared.TermAlg<Term, Ty, Function<Context<Bind>, String>> {
	PrintTy<Ty, Bind> printTy();
	PrintBind<Bind, Term, Ty> printBind();

	@Override
	default Function<Context<Bind>, String> TmAbs(String x, Ty ty, Term t) {
		return ctx -> {
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(x);
			return "lambda " + pr._2 + ":" + printTy().visitTy(ty) + "." + visitTerm(t).apply(pr._1);
		};
	}

	@Override
	default Function<Context<Bind>, String> TmApp(Term t1, Term t2) {
		return ctx -> visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmVar(int x, int n) {
		return ctx -> {
			if (ctx.length() == n)
				return ctx.index2Name(x);
			else
				return "[bad index: " + x + "/" + n + " in " + ctx.toString(printBind()) + "]";
		};
	}
}
