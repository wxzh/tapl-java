package fulluntyped;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fulluntyped.termalg.external.TermAlgMatcher;
import library.Tuple2;
import untyped.Context;

// TODO: The only way to reuse PrintArith is to extend it; delegation would not work
public interface Print<Term, Bind> extends fulluntyped.termalg.shared.TermAlg<Term, Function<Context<Bind>, String>>, untyped.Print<Term, Bind> {
	TermAlgMatcher<Term, String> matcher();

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
		return ctx -> "let " + x + "=" + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}

	default Function<Context<Bind>, String> TmFloat(float f) {
		return ctx -> String.valueOf(f);
	}

	default Function<Context<Bind>, String> TmTimesFloat(Term t1, Term t2) {
		return ctx -> "timesfloat " + visitTerm(t1).apply(ctx) + " " + visitTerm(t2).apply(ctx);
	}

	default Function<Context<Bind>, String> TmTrue() {
		return ctx -> "true";
	}

	default Function<Context<Bind>, String> TmFalse() {
		return ctx -> "false";
	}

	default Function<Context<Bind>, String> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> "if " + visitTerm(t1).apply(ctx) + " then " + visitTerm(t2).apply(ctx) + " else " + visitTerm(t3).apply(ctx);
	}

	default Function<Context<Bind>, String> TmZero() {
		return ctx -> "0";
	}

	default Function<Context<Bind>, String> TmSucc(Term t) {
		return ctx -> printConsecutiveSuccs(ctx, 1, t);
	}

	default String printConsecutiveSuccs(Context<Bind> ctx, int i, Term t) {
		return matcher()
				.TmSucc(t1 -> printConsecutiveSuccs(ctx, i+1, t1))
				.TmZero(() -> String.valueOf(i))
				.otherwise(() -> "(succ " + visitTerm(t).apply(ctx) + ")")
				.visitTerm(t);
	}

	default Function<Context<Bind>, String> TmPred(Term t) {
		return ctx -> "(pred " + visitTerm(t).apply(ctx) + ")";
	}

	default Function<Context<Bind>, String> TmIsZero(Term t) {
		return ctx -> "(iszero " + visitTerm(t).apply(ctx);
	}
}