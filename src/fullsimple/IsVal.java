package fullsimple;

import fullsimple.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty>
		extends TermAlgQuery<Term, Ty, Boolean>, moreextension.IsVal<Term, Ty>, typed.IsVal<Term, Ty>, variant.IsVal<Term, Ty> {
}
