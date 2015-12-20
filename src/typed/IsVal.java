package typed;

import simplebool.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, varapp.IsVal<Term> {
	@Override
	default Boolean TmAbs(String p1, Ty p2, Term p3) {
		return true;
	}
}
