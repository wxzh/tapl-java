package arith;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import utils.ZeroFalse;

public interface IsNumericVal<Term> extends TermAlgQuery<Term, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return new ZeroFalse();
	}

	default Boolean TmZero() {
		return true;
	}

	default Boolean TmSucc(Term t) {
		return visitTerm(t);
	}
}