package arith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import arith.termalg.external.ITerm;
import arith.termalg.external.TermAlgFactory;
import arith.termalg.external.TermAlgMatcher;
import arith.termalg.external.TermAlgMatcherImpl;
import arith.termalg.external.TermVisitor;
import utils.Eval;
import utils.NoRuleApplies;

public class Tests {

	class IsNumericalValImpl implements IsNumericVal<ITerm>, TermVisitor<Boolean> {
	}

	class IsValImpl implements IsVal<ITerm>, TermVisitor<Boolean> {
	}

	class PrintImpl implements Print<ITerm>, TermVisitor<String> {
		public TermAlgMatcher<ITerm, String> matcher() {
			return new TermAlgMatcherImpl<>();
		}
	}

	class Eval1Impl implements Eval1<ITerm>, TermVisitor<ITerm> {
		public arith.termalg.shared.TermAlg<ITerm, ITerm> alg() {
			return alg;
		}

		@Override
		public TermAlgMatcher<ITerm, ITerm> matcher() {
			return new TermAlgMatcherImpl<>();
		}

		@Override
		public IsNumericVal<ITerm> isNumericVal() {
			return isNumericalVal;
		}
	}

	class EvalImpl implements Eval<ITerm> {
		@Override
		public ITerm eval1(ITerm e) {
			return e.accept(eval1);
		}

		@Override
		public boolean isVal(ITerm e) {
			return e.accept(isVal);
		}
	}

	private TermAlgFactory alg = new TermAlgFactory();
	private PrintImpl print = new PrintImpl();
	private IsNumericalValImpl isNumericalVal = new IsNumericalValImpl();
	private IsValImpl isVal = new IsValImpl();
	private Eval1Impl eval1 = new Eval1Impl();
	private EvalImpl eval = new EvalImpl();

	private ITerm t = alg.TmTrue();
	private ITerm f = alg.TmFalse();
	private ITerm if_f_then_t_else_f = alg.TmIf(f, t, f);
	private ITerm zero = alg.TmZero();
	private ITerm pred_zero = alg.TmPred(zero);
	private ITerm succ_pred_0 = alg.TmSucc(alg.TmPred(zero));
	private ITerm pred_succ_0 = alg.TmPred(alg.TmSucc(zero));
	private ITerm succ_succ_0 = alg.TmSucc(alg.TmSucc(zero));
	private ITerm iszero_pred_succ_succ_0 = alg.TmIsZero(alg.TmPred(alg.TmSucc(alg.TmSucc(zero))));

	@Test
	public void printTest() {
		assertEquals("if false then true else false", if_f_then_t_else_f.accept(print));
		assertEquals("(iszero (pred 2))", iszero_pred_succ_succ_0.accept(print));
	}

	@Test
	public void IsNumericalValTest() {
		assertTrue(zero.accept(isNumericalVal));
		assertTrue(succ_succ_0.accept(isNumericalVal));
		assertFalse(pred_zero.accept(isNumericalVal));
		assertFalse(f.accept(isNumericalVal));
	}

	@Test
	public void isValTest() {
		assertTrue(isVal.visitTerm(t));
		assertFalse(isVal.visitTerm(if_f_then_t_else_f));
	}

	@Test(expected=NoRuleApplies.class)
	public void eval1TestNoRuleApplies(){
		t.accept(eval1);
	}

	public void eval1Test(){
		assertEquals("false", if_f_then_t_else_f.accept(eval1).accept(print));
	}

	@Test
	public void evalTest() {
		assertEquals("true", eval.eval(t).accept(print));
		assertEquals("false", eval.eval(if_f_then_t_else_f).accept(print));
		assertEquals("1", eval.eval(succ_pred_0).accept(print));
		assertEquals("0", eval.eval(pred_succ_0).accept(print));
		assertEquals("false", eval.eval(iszero_pred_succ_succ_0).accept(print));
	}
}