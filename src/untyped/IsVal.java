package untyped;

import library.Zero;
import untyped.termalg.shared.TermAlgQuery;
import utils.ZeroFalse;

public interface IsVal<E> extends TermAlgQuery<E, Boolean> {
	@Override
	default Zero<Boolean> m() {
		return new ZeroFalse();
	}

	default Boolean TmAbs(String p1, E p2) {
		return true;
	}
}
