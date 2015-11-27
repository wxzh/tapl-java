package arith;

public interface Print<Term> extends arith.termalg.shared.TermAlg<Term, String> {
	default String TmTrue() {
		return "true";
	}

	default String TmFalse() {
		return "false";
	}

	default String TmIf(Term cond, Term t1, Term t2) {
		return "if " + visitTerm(cond) + " then " + visitTerm(t1) + " else " + visitTerm(t2);
	}

	default String TmZero() {
		return "0";
	}

	default String TmSucc(Term t) {
		return "succ(" + visitTerm(t) + ")";
	}

	default String TmPred(Term t) {
		return "pred(" + visitTerm(t) + ")";
	}

	default String TmIsZero(Term t) {
		return "iszero(" + visitTerm(t) + ")";
	}
}