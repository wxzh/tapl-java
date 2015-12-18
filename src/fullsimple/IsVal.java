package fullsimple;

import fullsimple.termalg.shared.TermAlgQuery;

public interface IsVal<Term, Ty>
		extends TermAlgQuery<Term, Ty, Boolean>, extension.IsVal<Term>, typed.IsVal<Term, Ty>, variant.IsVal<Term, Ty> {
	@Override
	default Boolean TmUnit() {
		return true;
	}
}
