package arith;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsNumericalVal<I> extends TermAlgQuery<I, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return new Zero<Boolean>() {
			@Override
			public Boolean empty() {
				return false;
			}
		};
	}

	default Boolean TmZero() {
		return true;
	}

	default Boolean TmSucc(I t) {
		return visitTerm(t);
	}
}