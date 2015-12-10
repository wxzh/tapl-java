package untyped;

import library.Zero;
import untyped.termalg.shared.TermAlgQuery;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return () -> false;
	}

	default Boolean TmAbs(String p1, Term p2) {
		return true;
	}
}
