package fulluntyped;

import fulluntyped.extalg.shared.ExtAlgQuery;
import library.Zero;

public interface IsValExt<Term> extends ExtAlgQuery<Term, Boolean>, untyped.IsVal<Term>, record.IsVal<Term> {
	default Boolean TmFloat(float p1) {
		return true;
	}

	default Boolean TmString(String p1) {
		return true;
	}

	@Override
	default Zero<Boolean> m() {
		return untyped.IsVal.super.m();
	}
}
