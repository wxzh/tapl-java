package record;

import java.util.ArrayList;
import java.util.List;

import library.Tuple2;
import record.termalg.shared.TermAlgQuery;

public interface Eval1<Term> extends TermAlgQuery<Term, Term> {
	record.termalg.shared.TermAlg<Term, Term> alg();
	IsVal<Term> isVal();

	default Term TmRecord(List<Tuple2<String, Term>> fields) {
		return alg().TmRecord(evalAField(fields));
	}

	default List<Tuple2<String, Term>> evalAField(List<Tuple2<String, Term>> fields) {
		if (fields.size() == 0)
			m().empty();
		Tuple2<String, Term> pair = fields.get(0);
		List<Tuple2<String, Term>> rest = fields.subList(1, fields.size());
		List<Tuple2<String, Term>> xs;
		if (isVal().visitTerm(pair._2)) {
			xs = new ArrayList<>(evalAField(rest));
			xs.add(0, pair);
		} else {
			xs = new ArrayList<>(rest);
			xs.add(0, new Tuple2<>(pair._1, visitTerm(pair._2)));
		}
		return xs;
	}
}