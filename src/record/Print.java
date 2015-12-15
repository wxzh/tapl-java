package record;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import library.Tuple2;
import record.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TmRecord(List<Tuple2<String, Term>> fields) {
		return ctx -> "{" + IntStream.range(0, fields.size()).mapToObj(i -> {
			String l = fields.get(i)._1;
			String t = visitTerm(fields.get(i)._2).apply(ctx);
			return String.valueOf(i).equals(l) ? t : l + "=" + t;
		}).collect(Collectors.joining(",")) + "}";
	}

	default Function<Context<Bind>, String> TmProj(Term t, String l) {
		return ctx -> visitTerm(t).apply(ctx) + "." + l;
	}
}