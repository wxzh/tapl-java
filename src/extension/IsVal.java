package extension;

import extension.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, arith.IsVal<Term>, record.IsVal<Term> {
	default Boolean TmFloat(float p1) {
		return true;
	}

	default Boolean TmString(String p1) {
		return true;
	}

	@Override
	default Zero<Boolean> m() {
		return arith.IsVal.super.m();
	}
}
