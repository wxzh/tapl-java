package fulluntyped;

import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsNumericVal<Term> extends TermAlgQuery<Term, Boolean>, arith.IsNumericVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return arith.IsNumericVal.super.m();
	}
}