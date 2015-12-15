package nat;

import library.Zero;
import nat.termalg.shared.TermAlgQuery;

public interface IsNumericVal<Term> extends TermAlgQuery<Term, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return () -> false;
	}

	default Boolean TmZero() {
		return true;
	}

	default Boolean TmSucc(Term t) {
		return visitTerm(t);
	}
}