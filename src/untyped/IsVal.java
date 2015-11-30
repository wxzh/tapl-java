package untyped;

import library.Zero;
import untyped.termalg.shared.TermAlgQuery;
import utils.ZeroFalse;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return new ZeroFalse();
	}

	default Boolean TmAbs(String p1, Term p2) {
		return true;
	}
}
