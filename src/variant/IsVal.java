package variant;

import variant.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, typed.IsVal<Term, Ty> {
	@Override
	default Boolean TmTag(String x, Term t, Ty ty) {
		return visitTerm(t);
	}
}
