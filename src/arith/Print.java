package arith;

public interface Print<Term> extends PrintBool<Term>, PrintNat<Term> {
	default String TmIsZero(Term t) {
		return "(iszero " + visitTerm(t) + ")";
	}
}