package fullsimple;

import fullsimple.termalg.shared.TermAlgQuery;
import fulluntyped.IsValUntyped;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, IsValUntyped<Term>, simplebool.IsVal<Term, Ty> {
	@Override
	default Boolean TmApp(Term p1, Term p2) {
		return false;
	}

	@Override
	default Boolean TmTag(String x, Term t, Ty ty) {
		return visitTerm(t);
	}

	@Override
	default Boolean TmUnit() {
		return true;
	}
}
