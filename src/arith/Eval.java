package arith;

import utils.NoRuleApplies;

public interface Eval<I> {
	Eval1<I> eval1();
	Print<I> print();
	IsVal<I> isVal();

	default I eval(I t) {
		try {
			I t1 = eval1().visitTerm(t);
			return eval(t1);
		} catch(NoRuleApplies e) {
			if (isVal().visitTerm(t)) {
				return t;
			}
			else {
				throw e;
			}
		}
	}
}
