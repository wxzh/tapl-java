package arith;

public interface PrintBool<Term> extends arith.boolalg.shared.BoolAlg<Term, String>{
	default String TmTrue() {
		return "true";
	}

	default String TmFalse() {
		return "false";
	}

	default String TmIf(Term cond, Term t1, Term t2) {
		return "if " + visitTerm(cond) + " then " + visitTerm(t1) + " else " + visitTerm(t2);
	}
}
