package fulluntyped;

import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, arith.IsVal<Term>, IsValUntyped<Term> {
	@Override
	default Zero<Boolean> m() {
		return IsValUntyped.super.m();
	}
}
