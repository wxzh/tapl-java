package fulluntyped;

import fulluntyped.extalg.external.ExtAlgMatcher;
import fulluntyped.extalg.shared.ExtAlgQuery;

public interface Eval1Ext<Term, Bind> extends ExtAlgQuery<Term, Term>, arith.Eval1<Term>, record.Eval1<Term> {
	IsValExt<Term> isVal();
	@Override
	ExtAlgMatcher<Term, Term> matcher();
	fulluntyped.extalg.shared.ExtAlg<Term, Term> alg();

	@Override
	default Term TmTimesFloat(Term t1, Term t2) {
		return matcher()
				.TmFloat(f1 -> matcher().TmFloat(f2 -> alg().TmFloat(f1 * f2))
						.otherwise(() -> alg().TmTimesFloat(t1, visitTerm(t2)))
						.visitTerm(t2))
				.otherwise(() -> alg().TmTimesFloat(visitTerm(t1), t2))
				.visitTerm(t1);
	}

	@Override
	default Term TmProj(Term t, String l) {
		return matcher()
				.TmRecord(fields -> isVal().visitTerm(t)
						? fields.stream().filter(pr -> pr._1.equals(l)).findFirst()
								.map(pr -> pr._2).orElseGet(() -> m().empty()) // should be lazy for throwing errors
						: alg().TmProj(visitTerm(t), l))
				.otherwise(() -> alg().TmProj(visitTerm(t), l))
				.visitTerm(t);
	}
}
