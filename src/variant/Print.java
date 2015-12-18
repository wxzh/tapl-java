package variant;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import library.Tuple2;
import library.Tuple3;
import utils.Context;
import variant.termalg.shared.GTermAlg;

public interface Print<Term, Ty, Bind> extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, typed.Print<Term, Ty, Bind> {
	PrintTy<Ty, Bind> printTy();

	@Override
	default Function<Context<Bind>, String> TmTag(String label, Term t, Ty ty) {
		return ctx -> "<" + label + "=" + visitTerm(t).apply(ctx) + "> as " + printTy().visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmCase(Term t, List<Tuple3<String, String, Term>> cases) {
		return ctx -> "case " + visitTerm(t).apply(ctx) + " of " + cases.stream().map(c -> {
			String label = c._1;
			Tuple2<Context<Bind>, String> pr = ctx.pickFreshName(c._2);
			return "<" + label + "=" + pr._2 + ">==>" + visitTerm(c._3).apply(pr._1);
		}).collect(Collectors.joining("| "));
	}

}