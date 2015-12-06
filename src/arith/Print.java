package arith;

import arith.termalg.external.TermAlgMatcher;

public interface Print<Term> extends PrintBool<Term>, PrintNat<Term> {
	TermAlgMatcher<Term, String> matcher();
	default String TmIsZero(Term t) {
		return "(iszero " + visitTerm(t) + ")";
	}
}