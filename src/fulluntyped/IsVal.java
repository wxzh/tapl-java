package fulluntyped;

import fulluntyped.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, extension.IsVal<Term>, untyped.IsVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return extension.IsVal.super.m();
	}
}
