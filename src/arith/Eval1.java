package arith;

import arith.termalg.external.TermAlgMatcher;
import arith.termalg.shared.TermAlgTransform;
import utils.NoRuleApplies;

// 1. empty(): True False Zero can be omitted
// 2. transform: Succ can be omitted
public interface Eval1<I> extends TermAlgTransform<I> {
	TermAlgMatcher<I, I> matcher();
	IsNumericalVal<I> isNumericalVal();

	@Override
	default I TmIf(I t1, I t2, I t3) {
		return matcher()
				.TmTrue(() -> t2)
				.TmFalse(() -> t3)
				.otherwise(() -> alg().TmIf(visitTerm(t1), t2, t3))
				.visitTerm(t1);
	}

	@Override
	default I TmPred(I t) {
		return matcher()
				.TmZero(() -> t)
				.TmSucc(nv1 -> isNumericalVal().visitTerm(nv1) ? nv1 : TmPred(visitTerm(t)))
				.otherwise(() -> alg().TmPred(visitTerm(t)))
				.visitTerm(t);
	}

	@Override
	default I TmIsZero(I t) {
		return matcher()
				.TmZero(() -> alg().TmTrue())
				.TmSucc(nv1 -> isNumericalVal().visitTerm(nv1) ? alg().TmFalse() : alg().TmIsZero(visitTerm(t)))
				.otherwise(() -> alg().TmIsZero(visitTerm(t)))
				.visitTerm(t);
	}

	@Override
	default I TmTrue() {
		throw new NoRuleApplies("TmTrue");
	}

	@Override
	default I TmFalse() {
		throw new NoRuleApplies("TmFalse");
	}

	@Override
	default I TmZero() {
		throw new NoRuleApplies("TmZero");
	}
}