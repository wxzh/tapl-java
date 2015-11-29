package arith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import arith.termalg.external.TermAlgFactory;
import arith.termalg.external.TermAlgMatcher;
import arith.termalg.external.TermAlgMatcherImpl;
import arith.termalg.external.TermAlgTermElement;
import arith.termalg.external.TermAlgTermVisitor;
import utils.Eval;
import utils.NoRuleApplies;

public class Tests {

	class IsNumericalValImpl implements IsNumericalVal<TermAlgTermElement>, TermAlgTermVisitor<Boolean> {
	}

	class IsValImpl implements IsVal<TermAlgTermElement>, TermAlgTermVisitor<Boolean> {
	}

	class PrintImpl implements Print<TermAlgTermElement>, TermAlgTermVisitor<String> {
		@Override
		public TermAlgMatcher<TermAlgTermElement, String> matcher() {
			return new TermAlgMatcherImpl<>();
		}
	}

	class Eval1Impl implements Eval1<TermAlgTermElement>, TermAlgTermVisitor<TermAlgTermElement> {
		public arith.termalg.shared.TermAlg<TermAlgTermElement, TermAlgTermElement> alg() {
			return alg;
		}

		@Override
		public TermAlgMatcher<TermAlgTermElement, TermAlgTermElement> matcher() {
			return new TermAlgMatcherImpl<>();
		}

		@Override
		public IsNumericalVal<TermAlgTermElement> isNumericalVal() {
			return isNumericalVal;
		}
	}

	class EvalImpl implements Eval<TermAlgTermElement> {
		@Override
		public TermAlgTermElement eval1(TermAlgTermElement e) {
			return e.accept(eval1);
		}

		@Override
		public String print(TermAlgTermElement e) {
			return e.accept(print);
		}

		@Override
		public boolean isVal(TermAlgTermElement e) {
			return e.accept(isVal);
		}
	}

	private TermAlgFactory alg = new TermAlgFactory();
	private PrintImpl print = new PrintImpl();
	private IsNumericalValImpl isNumericalVal = new IsNumericalValImpl();
	private IsValImpl isVal = new IsValImpl();
	private Eval1Impl eval1 = new Eval1Impl();
	private EvalImpl eval = new EvalImpl();

	private TermAlgTermElement t = alg.TmTrue();
	private TermAlgTermElement f = alg.TmFalse();
	private TermAlgTermElement if_f_then_t_else_f = alg.TmIf(f, t, f);
	private TermAlgTermElement zero = alg.TmZero();
	private TermAlgTermElement pred_zero = alg.TmPred(zero);
	private TermAlgTermElement succ_pred_0 = alg.TmSucc(alg.TmPred(zero));
	private TermAlgTermElement pred_succ_0 = alg.TmPred(alg.TmSucc(zero));
	private TermAlgTermElement succ_succ_0 = alg.TmSucc(alg.TmSucc(zero));
	private TermAlgTermElement iszero_pred_succ_succ_0 = alg.TmIsZero(alg.TmPred(alg.TmSucc(alg.TmSucc(zero))));

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