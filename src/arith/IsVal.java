package arith;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import utils.ZeroFalse;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, IsNumericVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return new ZeroFalse();
	}

	@Override
	default Boolean TmTrue() {
		return true;
	}

	@Override
	default Boolean TmFalse() {
		return true;
	}
}