package fullsimple;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import fullsimple.termalg.external.TermAlgMatcher;
import fulluntyped.PrintUntyped;
import library.Tuple2;
import library.Tuple3;
import utils.Context;

public interface Print<Term, Ty, Bind>
		extends fullsimple.termalg.shared.TermAlg<Term, Ty, Function<Context<Bind>, String>>, simplebool.Print<Term, Ty, Bind>, PrintUntyped<Term, Bind> {
	@Override
	TermAlgMatcher<Term, Ty, String> matcher();
	@Override
	PrintTy<Ty, Bind> printTy();
	@Override
	PrintBind<Bind, Term, Ty> printBind();

	@Override
	default Function<Context<Bind>, String> TmInert(Ty ty) {
		return ctx -> "inert[" + printTy().visitTy(ty).apply(ctx) + "]";
	}

	@Override
	default Function<Context<Bind>, String> TmFix(Term t) {
		return ctx -> "fix " + visitTerm(t).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmTag(String label, Term t, Ty ty) {
		return ctx -> "<" + label + "=" + visitTerm(t).apply(ctx) + "> as " + printTy().visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmAscribe(Term t, Ty ty) {
		return ctx -> visitTerm(t).apply(ctx) + " as " + printTy().visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmCase(Term t, List<Tuple3<String, String, Term>> cases) {
		return ctx -> "case " + visitTerm(t).apply(ctx) + " of " + cases.stream().map(c -> {
			String label = c._1;
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(c._2);
			return "<" + label + "=" + pr._2 + ">==>" + visitTerm(c._3).apply(pr._1);
		}).collect(Collectors.joining("| "));
	}

	@Override
	default Function<Context<Bind>, String> TmUnit() {
		return ctx -> "Unit";
	}

	default Function<utils.Context<Bind>,String> TmLet(String x, Term t1, Term t2) {
		return ctx -> "let " + x + "=" + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}
}