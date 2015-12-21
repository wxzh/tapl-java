package fullref;

import fullref.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, fullsimple.IsVal<Term, Ty> {
	@Override
	default Boolean TmLoc(int l) {
		return true;
	}
}