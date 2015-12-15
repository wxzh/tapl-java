package arith;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, bool.IsVal<Term>, nat.IsNumericVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return bool.IsVal.super.m();
	}
}