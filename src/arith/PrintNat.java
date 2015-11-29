package arith;

import arith.termalg.external.TermAlgMatcher;

public interface PrintNat<Term> extends arith.natalg.shared.NatAlg<Term, String>{
	TermAlgMatcher<Term, String> matcher();

	default String TmZero() {
		return "0";
	}

	default String TmSucc(Term t) {
		return printConsecutiveSuccs(1, t);
	}

	default String TmPred(Term t) {
		return "(pred " + visitTerm(t) + ")";
	}

	default String printConsecutiveSuccs(int i, Term t) {
		return matcher()
				.TmSucc(t1 -> printConsecutiveSuccs(i+1, t1))
				.TmZero(() -> String.valueOf(i))
				.otherwise(() -> "(succ " + visitTerm(t) + ")")
				.visitTerm(t);
	}
}
