package fullsimple;

import fullsimple.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsNumericVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, arith.IsNumericVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return arith.IsNumericVal.super.m();
	}
}
