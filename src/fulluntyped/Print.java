package fulluntyped;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import library.Tuple2;
import untyped.Context;

public interface Print<Bind, Term> extends fulluntyped.termalg.shared.TermAlg<Term, Function<Context<Bind>, String>>, untyped.Print<Term, Bind> {
	fulluntyped.termalg.shared.TermAlg<Term, Term> alg();
	arith.Print<Term> arithPrinter();

	default Function<Context<Bind>, String> TmString(String s) {
		return ctx -> s;
	}

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

	default Function<Context<Bind>, String> TmLet(String x, Term t1, Term t2) {
		return ctx -> "let " + x + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}

	default Function<Context<Bind>, String> TmFloat(float f) {
		return ctx -> String.valueOf(f);
	}

	// delegation
	default Function<Context<Bind>, String> TmTimesFloat(Term t1, Term t2) {
		return ctx -> "timesfloat " + visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	default Function<Context<Bind>, String> TmTrue() {
		return ctx -> arithPrinter().visitTerm(alg().TmTrue());
	}

	default Function<Context<Bind>, String> TmFalse() {
		return ctx -> arithPrinter().visitTerm(alg().TmFalse());
	}

	default Function<Context<Bind>, String> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> arithPrinter().visitTerm(alg().TmIf(t1, t2, t3));
	}

	default Function<Context<Bind>, String> TmZero() {
		return ctx -> arithPrinter().visitTerm(alg().TmZero());
	}

	default Function<Context<Bind>, String> TmSucc(Term t) {
		return ctx -> arithPrinter().visitTerm(alg().TmSucc(t));
	}

	default Function<Context<Bind>, String> TmPred(Term t) {
		return ctx -> arithPrinter().visitTerm(alg().TmPred(t));
	}

	default Function<Context<Bind>, String> TmIsZero(Term t) {
		return ctx -> arithPrinter().visitTerm(alg().TmIsZero(t));
	}
}
