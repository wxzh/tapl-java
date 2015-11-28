package arith;

public interface PrintNat<Term> extends arith.natalg.shared.NatAlg<Term, String>{
	default String TmZero() {
		return "0";
	}

	default String TmSucc(Term t) {
		return "succ(" + visitTerm(t) + ")";
	}

	default String TmPred(Term t) {
		return "pred(" + visitTerm(t) + ")";
	}
}
