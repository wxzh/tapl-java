package untyped;

import untyped.termalg.external.TermAlgMatcher;
import untyped.termalg.shared.TermAlgTransform;
import utils.NoRuleApplies;

public interface Eval1<Term> extends TermAlgTransform<Term> {
	IsVal<Term> isVal();
	TermAlgMatcher<Term, Term> matcher();

	interface VarMapper<E> {
		E apply(int c, int x, int n);
	}

	default Term tmMap(VarMapper<Term> onVar, int c, Term t) {
		return matcher().TmVar((x, n) -> onVar.apply(c, x, n))
				.TmAbs((x, t2) -> alg().TmAbs(x, tmMap(onVar, c + 1, t2)))
				.TmApp((t1, t2) -> alg().TmApp(tmMap(onVar, c, t1), tmMap(onVar, c, t1))).otherwise(() -> t)
				.visitTerm(t);

	}

	default Term termShiftAbove(int d, int c, Term t) {
		return tmMap((c1, x, n) -> x >= c1 ? alg().TmVar(x + d, n + d) : alg().TmVar(x, n + d), c, t);
	}

	default Term termShift(int d, Term t) {
		return termShiftAbove(d, 0, t);
	}

	default Term termSubst(int j, Term s, Term t) {
		return tmMap((c, x, n) -> x == c ? termShift(j, s) : alg().TmVar(x, n), j, t);
	}

	default Term termSubstTop(Term s, Term t) {
		return termShift(-1, termSubst(0, termShift(1, s), t));
	}

	default Term TmApp(Term t1, Term t2) {
		return matcher()
				.TmAbs((x, t) ->
					isVal().visitTerm(t2) ? termSubstTop(t2, t) : (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(() ->
					isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2))
				.visitTerm(t1);
	}

	@Override
	default Term TmAbs(String x, Term t) {
		throw new NoRuleApplies("TmAbs: " + x);
	}

	default Term TmVar(int x, int n) {
		throw new NoRuleApplies("TmVar: " + x);
	}
}