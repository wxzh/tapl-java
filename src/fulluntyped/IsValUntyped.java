package fulluntyped;

import java.util.List;

import fulluntyped.untypedalg.shared.UntypedAlgQuery;
import library.Tuple2;

public interface IsValUntyped<Term> extends UntypedAlgQuery<Term, Boolean>, untyped.IsVal<Term> {
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
