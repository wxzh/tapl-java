package fullsimple;

import fullsimple.termalg.shared.TermAlgQuery;
import fulluntyped.IsValExt;
import library.Zero;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, IsValExt<Term>, simplebool.IsVal<Term, Ty>, record.IsVal<Term> {
	@Override
	default Boolean TmTag(String x, Term t, Ty ty) {
		return visitTerm(t);
	}

	@Override
	default Boolean TmUnit() {
		return true;
	}

	@Override
	default Zero<Boolean> m() {
		return simplebool.IsVal.super.m();
	}
}
