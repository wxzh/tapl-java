package nat;

import java.util.function.Function;

import nat.termalg.external.TermAlgMatcher;
import nat.termalg.shared.GTermAlg;
import utils.Context;

public interface Print<Term, Bind> extends GTermAlg<Term, Function<Context<Bind>, String>> {
	TermAlgMatcher<Term, String> matcher();

	default Function<Context<Bind>, String> TmZero() {
		return ctx -> "0";
	}

	default Function<Context<Bind>, String> TmSucc(Term t) {
		return ctx -> printConsecutiveSuccs(1, t, ctx);
	}

	default Function<Context<Bind>, String> TmPred(Term t) {
		return ctx -> "(pred " + visitTerm(t).apply(ctx) + ")";
	}

	default String printConsecutiveSuccs(int i, Term t, Context<Bind> ctx) {
		return matcher()
				.TmSucc(t1 -> printConsecutiveSuccs(i+1, t1, ctx))
				.TmZero(() -> String.valueOf(i))
				.otherwise(() -> "(succ " + visitTerm(t).apply(ctx) + ")")
				.visitTerm(t);
	}
}
