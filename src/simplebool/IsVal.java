package simplebool;

import simplebool.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean> {
	@Override
	default Boolean TmTrue() {
		return true;
	}

	@Override
	default Boolean TmFalse() {
		return true;
	}

	@Override
	default Boolean TmAbs(String p1, Ty p2, Term p3) {
		return true;
	}

}
