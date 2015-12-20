package moreextension;

import moreextension.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty> extends TermAlgQuery<Term, Ty, Boolean>, extension.IsVal<Term> {
	default Boolean TmUnit() {
		return true;
	}
}