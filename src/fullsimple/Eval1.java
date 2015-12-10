package fullsimple;

import java.util.List;

import fullsimple.termalg.external.TermAlgMatcher;
import fullsimple.termalg.shared.TermAlgQuery;
import fulluntyped.Eval1Untyped;
import library.Tuple3;
import utils.Context;

// TODO: is ctx changed while evaluation?
public interface Eval1<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Term>, Eval1Untyped<Term, Bind>, TermShiftAndSubst<Term, Ty> {
	IsVal<Term, Ty> isVal();
	fullsimple.termalg.shared.TermAlg<Term, Ty, Term> alg();
	TermAlgMatcher<Term, Ty, Term> matcher();
	Context<Bind> ctx();

	@Override
	default Term TmTag(String label, Term t, Ty ty) {
		return alg().TmTag(label, visitTerm(t), ty);
	}

	default Term TmAscribe(Term t, Ty ty) {
		return isVal().visitTerm(t) ? t : alg().TmAscribe(visitTerm(t), ty);
	}

	default Term TmCase(Term t, List<Tuple3<String, String, Term>> branches) {
		return matcher()
				.TmTag(label -> ty -> t1 -> isVal().visitTerm(t) ? branches.stream().filter(b -> b._1.equals(label))
						.findFirst().map(b -> termSubstTop(t, b._3)).orElseGet(() -> m().empty())
						: alg().TmCase(visitTerm(t), branches))
				.otherwise(() -> alg().TmCase(visitTerm(t), branches))
				.visitTerm(t);
	}

	@Override
	default Term TmFix(Term t) {
		return alg().TmFix(visitTerm(t));
	}
}

// TODO: bindingShift.visitBind(ctx.getBinding(i)).(i + 1)