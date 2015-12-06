package fulluntyped;

import java.util.List;

import fulluntyped.termalg.shared.TermAlgQuery;
import library.Tuple2;
import library.Zero;
import utils.ZeroFalse;

public interface IsVal<Term> extends TermAlgQuery<Term, Boolean>, arith.IsVal<Term>, untyped.IsVal<Term> {
	@Override
	default Zero<Boolean> m() {
		return new ZeroFalse();
	}

	default Boolean TmFloat(float p1) {
		return true;
	}

	default Boolean TmString(String p1) {
		return true;
	}

	default Boolean TmRecord(List<Tuple2<String, Term>> fields) {
		return fields.stream().map(pr -> visitTerm(pr._2)).reduce(true, (x, y) -> x && y);
	}
}
