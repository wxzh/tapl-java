package untyped;

import untyped.termalg.external.TermAlgMatcher;
import untyped.termalg.shared.TermAlgTransform;
import utils.NoRuleApplies;

public interface Eval1<E> extends TermAlgTransform<E> {
	IsVal<E> isVal();
	TermAlgMatcher<E, E> matcher();

	interface VarMapper<E> {
		E apply(int c, int x, int n);
	}

	default E tmMap(VarMapper<E> onVar, int c, E t) {
		return matcher().TmVar((x, n) -> onVar.apply(c, x, n))
				.TmAbs((x, t2) -> alg().TmAbs(x, tmMap(onVar, c + 1, t2)))
				.TmApp((t1, t2) -> alg().TmApp(tmMap(onVar, c, t1), tmMap(onVar, c, t1))).otherwise(() -> t)
				.visitTerm(t);

	}

	default E termShiftAbove(int d, int c, E t) {
		return tmMap((c1, x, n) -> x >= c1 ? alg().TmVar(x + d, n + d) : alg().TmVar(x, n + d), c, t);
	}

	default E termShift(int d, E t) {
		return termShiftAbove(d, 0, t);
	}

	default E termSubst(int j, E s, E t) {
		return tmMap((c, x, n) -> x == c ? termShift(j, s) : alg().TmVar(x, n), j, t);
	}

	default E termSubstTop(E s, E t) {
		return termShift(-1, termSubst(0, termShift(1, s), t));
	}

	default E TmApp(E t1, E t2) {
		return matcher()
				.TmAbs((x, t) ->
					isVal().visitTerm(t2) ? termSubstTop(t2, t) : (isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2)))
				.otherwise(() ->
					isVal().visitTerm(t1) ? alg().TmApp(t1, visitTerm(t2)) : alg().TmApp(visitTerm(t1), t2))
				.visitTerm(t1);
	}

	@Override
	default E TmAbs(String p1, E p2) {
		throw new NoRuleApplies("TmAbs");
	}

	default E TmVar(int p1, int p2) {
		throw new NoRuleApplies("TmVar");
	}
}