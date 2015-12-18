package fullsimple;

import java.util.List;

import fullsimple.termalg.external.TermAlgMatcher;
import fullsimple.termalg.shared.GTermAlg;
import fullsimple.termalg.shared.TermAlgQuery;
import library.Tuple3;
import library.Zero;
import utils.Context;

public interface Eval1<Term, Ty, Bind>
		extends TermAlgQuery<Term, Ty, Term>, extension.Eval1<Term, Bind>, variant.Eval1<Term, Ty> {
	Context<Bind> ctx();

	@Override
	IsVal<Term, Ty> isVal();

	@Override
	GTermAlg<Term, Ty, Term> alg();

	@Override
	TermAlgMatcher<Term, Ty, Term> matcher();

	@Override
	TermShiftAndSubst<Term, Ty> termShiftAndSubst();

	@Override
	default Term TmTag(String label, Term t, Ty ty) {
		return alg().TmTag(label, visitTerm(t), ty);
	}

	default Term TmAscribe(Term t, Ty ty) {
		return isVal().visitTerm(t) ? t : alg().TmAscribe(visitTerm(t), ty);
	}

	default Term TmCase(Term t, List<Tuple3<String, String, Term>> branches) {
		return matcher()
				.TmTag(label -> ty -> t1 -> isVal().visitTerm(t)
						? branches.stream().filter(b -> b._1.equals(label)).findFirst()
								.map(b -> termShiftAndSubst().termSubstTop(t, b._3)).orElseGet(() -> m().empty())
						: alg().TmCase(visitTerm(t), branches))
				.otherwise(() -> alg().TmCase(visitTerm(t), branches)).visitTerm(t);
	}

	@Override
	default Term TmFix(Term t) {
		return alg().TmFix(visitTerm(t));
	}

	@Override
	default Zero<Term> m() {
		return extension.Eval1.super.m();
	}
}