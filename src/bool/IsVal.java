package bool;


import bool.termalg.shared.TermAlgQuery;
import library.Zero;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return () -> false;
	}

	@Override
	default Boolean TmTrue() {
		return true;
	}

	@Override
	default Boolean TmFalse() {
		return true;
	}
}