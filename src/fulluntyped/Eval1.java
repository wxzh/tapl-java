package fulluntyped;

import java.util.ArrayList;
import java.util.List;

import fulluntyped.bindingalg.external.BindingAlgMatcher;
import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.shared.TermAlgQuery;
import library.Tuple2;
import library.Zero;
import untyped.Context;
import untyped.TmMap;
import utils.NoRuleApplies;

public interface Eval1<Term, Bind> extends TermAlgQuery<Term, Term>, untyped.Eval1<Term>, arith.Eval1<Term> {
	default Zero<Term> m() {
		return arith.Eval1.super.m();
	}

	TermAlgMatcher<Term, Term> matcher();
	BindingAlgMatcher<Bind, Term, Term> bindMatcher();
	fulluntyped.termalg.shared.TermAlg<Term, Term> alg();
	Context<Bind> ctx();
	TmMap<Term> tmMap();

	default Term TmRecord(List<Tuple2<String, Term>> fields) {
		return alg().TmRecord(evalAField(fields));
	}

	default List<Tuple2<String, Term>> evalAField(List<Tuple2<String, Term>> fields) {
		if (fields.size() == 0)
			throw new NoRuleApplies();
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

	@Override
	default Term TmLet(String x, Term t1, Term t2) {
		return isVal().visitTerm(t1) ? termSubstTop(t1, t2) : alg().TmLet(x, visitTerm(t1), t2);
	}

	@Override
	default Term TmTimesFloat(Term t1, Term t2) {
		return matcher()
				.TmFloat(f1 -> matcher().TmFloat(f2 -> alg().TmFloat(f1 * f2))
						.otherwise(() -> alg().TmTimesFloat(t1, visitTerm(t2)))
						.visitTerm(t2))
				.otherwise(() -> alg().TmTimesFloat(visitTerm(t1), t2))
				.visitTerm(t1);
	}

	default Term TmVar(int x, int n) {
		return bindMatcher()
				.TmAbbBind(t -> t)
				.otherwise(() -> m().empty())
				.visitBind(ctx().getBinding(x));
	}

	@Override
	default Term TmProj(Term t, String l) {
		System.out.println(l);
		return matcher()
				.TmRecord(fields -> isVal().visitTerm(t)
						? fields.stream().filter(pr -> pr._1.equals(l)).findFirst()
								.map(pr -> pr._2).orElseGet(() -> m().empty()) // should be lazy for throwing errors
						: alg().TmProj(visitTerm(t), l))
				.otherwise(() -> alg().TmProj(visitTerm(t), l))
				.visitTerm(t);
	}
}
