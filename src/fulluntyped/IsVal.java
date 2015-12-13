package fulluntyped;

import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, arith.IsVal<Term>, IsValExt<Term> {
	@Override
	default Zero<Boolean> m() {
		return IsValExt.super.m();
	}
}
