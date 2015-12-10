package fulluntyped;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Function;

import org.junit.Test;

import fulluntyped.bindingalg.external.BindingAlgBindElement;
import fulluntyped.bindingalg.external.BindingAlgBindVisitor;
import fulluntyped.bindingalg.external.BindingAlgFactory;
import fulluntyped.bindingalg.external.BindingAlgMatcher;
import fulluntyped.bindingalg.external.BindingAlgMatcherImpl;
import fulluntyped.termalg.external.TermAlgFactory;
import fulluntyped.termalg.external.TermAlgMatcher;
import fulluntyped.termalg.external.TermAlgMatcherImpl;
import fulluntyped.termalg.external.TermAlgTermElement;
import fulluntyped.termalg.external.TermAlgTermVisitor;
import fulluntyped.termalg.shared.TermAlg;
import library.Tuple2;
import utils.Context;
import utils.Eval;

public class Tests {

	class PrintImpl implements Print<TermAlgTermElement, BindingAlgBindElement<TermAlgTermElement>>,
			TermAlgTermVisitor<Function<Context<BindingAlgBindElement<TermAlgTermElement>>, String>> {
		public TermAlgMatcher<TermAlgTermElement, String> matcher() {
			return new TermAlgMatcherImpl<>();
		}

		@Override
		public PrintBind<BindingAlgBindElement<TermAlgTermElement>, TermAlgTermElement> printBind() {
			return new PrintBindImpl();
		}
	}

	class PrintBindImpl implements PrintBind<BindingAlgBindElement<TermAlgTermElement>, TermAlgTermElement>,
			BindingAlgBindVisitor<Function<Context<BindingAlgBindElement<TermAlgTermElement>>, String>, TermAlgTermElement> {
		@Override
		public Print<TermAlgTermElement, BindingAlgBindElement<TermAlgTermElement>> printTerm() {
			return new PrintImpl();
		}
	}

	class IsNumericValImpl implements IsNumericVal<TermAlgTermElement>, TermAlgTermVisitor<Boolean> {
	}

	class IsValImpl implements fulluntyped.IsVal<TermAlgTermElement>, TermAlgTermVisitor<Boolean> {
	}

	class Eval1Impl implements Eval1<TermAlgTermElement, BindingAlgBindElement<TermAlgTermElement>>,
			TermAlgTermVisitor<TermAlgTermElement> {
		public Context<BindingAlgBindElement<TermAlgTermElement>> ctx() {
			return ctx;
		}

		@Override
		public IsNumericVal<TermAlgTermElement> isNumericVal() {
			return new IsNumericValImpl();
		}

		@Override
		public IsVal<TermAlgTermElement> isVal() {
			return isVal;
		}

		class TmMapImpl implements TmMap<TermAlgTermElement>,
				TermAlgTermVisitor<Function<Tuple2<TmMap.VarMapper<TermAlgTermElement>, Integer>, TermAlgTermElement>> {
			public TermAlg<TermAlgTermElement, TermAlgTermElement> alg() {
				return fact;
			}
		}

		@Override
		public TmMap<TermAlgTermElement> tmMap() {
			return new TmMapImpl();
		}

		@Override
		public BindingAlgMatcher<BindingAlgBindElement<TermAlgTermElement>, TermAlgTermElement, TermAlgTermElement> bindMatcher() {
			return new BindingAlgMatcherImpl<>();
		}

		@Override
		public TermAlg<TermAlgTermElement, TermAlgTermElement> alg() {
			return fact;
		}

		public TermAlgMatcher<TermAlgTermElement, TermAlgTermElement> matcher() {
			return new TermAlgMatcherImpl<>();
		}
	}

	class EvalImpl implements Eval<TermAlgTermElement> {
		public TermAlgTermElement eval1(TermAlgTermElement t) {
			return t.accept(eval1);
		}

		public String print(TermAlgTermElement t) {
			return t.accept(print).apply(ctx);
		}

		@Override
		public boolean isVal(TermAlgTermElement t) {
			return t.accept(isVal);
		}

	}

	TermAlgFactory fact = new TermAlgFactory();
	BindingAlgFactory<TermAlgTermElement> bindFact = new BindingAlgFactory<>();
	Context<BindingAlgBindElement<TermAlgTermElement>> ctx = new Context<>(new BindingAlgFactory<>());
	PrintImpl print = new PrintImpl();
	PrintBindImpl printBind = new PrintBindImpl();
	IsValImpl isVal = new IsValImpl();
	Eval1Impl eval1 = new Eval1Impl();
	EvalImpl eval = new EvalImpl();
	TermAlgTermElement t = fact.TmTrue();
	TermAlgTermElement f = fact.TmFalse();
	TermAlgTermElement if_f_then_t_else_f = fact.TmIf(f, t, f);
	TermAlgTermElement x = fact.TmVar(0, 1);
	TermAlgTermElement id = fact.TmAbs("x", x);
	TermAlgTermElement lam_x_xx = fact.TmAbs("x", fact.TmApp(x, x));
	TermAlgTermElement id_lam_x_xx = fact.TmApp(id, fact.TmAbs("x", fact.TmApp(x, x)));
	TermAlgTermElement record = fact.TmRecord(Arrays.asList(new Tuple2<>("x", id), new Tuple2<>("y", id_lam_x_xx)));
	TermAlgTermElement proj = fact.TmProj(record, "x");
	TermAlgTermElement hello = fact.TmString("hello");
	TermAlgTermElement timesfloat = fact.TmTimesFloat(fact.TmTimesFloat(fact.TmFloat(2f), fact.TmFloat(3f)),
			fact.TmTimesFloat(fact.TmFloat(4f), fact.TmFloat(5f)));
	TermAlgTermElement o = fact.TmZero();
	TermAlgTermElement succ_pred_0 = fact.TmSucc(fact.TmPred(o));
	TermAlgTermElement let_x_t_in_x = fact.TmLet("x", t, x);
	TermAlgTermElement mixed = fact.TmLet("t", fact.TmApp(proj, t), fact.TmIf(fact.TmVar(0, 1), o, succ_pred_0));

	Context<BindingAlgBindElement<TermAlgTermElement>> ctx2 = ctx.addBinding("x", bindFact.TmAbbBind(t)).addName("y");

	@Test
	public void testPrint() {
		assertEquals("true", t.accept(print).apply(ctx));
		assertEquals("if false then true else false", if_f_then_t_else_f.accept(print).apply(ctx));
		assertEquals("x", x.accept(print).apply(ctx.addName("x")));
		assertEquals("\\x.x", id.accept(print).apply(ctx));
		assertEquals("\\x.x \\x.x x", id_lam_x_xx.accept(print).apply(ctx));
		assertEquals("{x=\\x.x,y=\\x.x \\x.x x}", record.accept(print).apply(ctx));
		assertEquals("{x=\\x.x,y=\\x.x \\x.x x}.x", proj.accept(print).apply(ctx));
		assertEquals("hello", hello.accept(print).apply(ctx));
		assertEquals("timesfloat timesfloat 2.0 3.0 timesfloat 4.0 5.0", timesfloat.accept(print).apply(ctx));
		assertEquals("0", o.accept(print).apply(ctx));
		assertEquals("(succ (pred 0))", succ_pred_0.accept(print).apply(ctx));
		assertEquals("let x=true in x", let_x_t_in_x.accept(print).apply(ctx));
		assertEquals("let t={x=\\x.x,y=\\x.x \\x.x x}.x true in if t then 0 else (succ (pred 0))", mixed.accept(print).apply(ctx));
	}

	@Test
	public void testIsVal() {
		assertTrue(fact.TmRecord(Collections.emptyList()).accept(isVal));
		assertTrue(fact.TmRecord(Collections.singletonList(new Tuple2<>("x", id))).accept(isVal));
		assertFalse(fact.TmRecord(Collections.singletonList(new Tuple2<>("x", id_lam_x_xx))).accept(isVal));
		assertFalse(record.accept(isVal));
		assertTrue(fact.TmRecord(Collections.singletonList(new Tuple2<>("x", t))).accept(isVal));
		assertTrue(fact.TmRecord(Arrays.asList(new Tuple2<>("x", t), new Tuple2<>("y", f))).accept(isVal));
	}

	@Test
	public void testEval() {
		assertEquals("true", eval.eval(t).accept(print).apply(ctx));
		assertEquals("false", eval.eval(if_f_then_t_else_f).accept(print).apply(ctx));
		assertEquals("\\x.x", eval.eval(id).accept(print).apply(ctx));
		assertEquals("0", eval.eval(fact.TmApp(id, o)).accept(print).apply(ctx));
		assertEquals("\\x.x x", eval.eval(id_lam_x_xx).accept(print).apply(ctx));
		assertEquals("{x=\\x.x,y=\\x.x x}", eval.eval(record).accept(print).apply(ctx));
		assertEquals("\\x.x", eval.eval(proj).accept(print).apply(ctx));
		assertEquals("hello", eval.eval(hello).accept(print).apply(ctx));
		assertEquals("0.0", eval.eval(fact.TmFloat(0l)).accept(print).apply(ctx));
		assertEquals("120.0", eval.eval(timesfloat).accept(print).apply(ctx));
		assertEquals("0", eval.eval(o).accept(print).apply(ctx));
		assertEquals("1", eval.eval(succ_pred_0).accept(print).apply(ctx));
		assertEquals("true", eval.eval(let_x_t_in_x).accept(print).apply(ctx));
		assertEquals("0", eval.eval(mixed).accept(print).apply(ctx));
	}

	@Test
	public void testContext() throws Exception {
		assertEquals("{}", ctx.toString(printBind));
		assertEquals("{(x,)}", ctx.addName("x").toString(printBind));
		assertEquals("{(y,), (x,true)}", ctx2.toString(printBind));
		assertEquals("y", ctx2.index2Name(0));
		assertEquals("x", ctx2.index2Name(1));
		assertEquals(0, ctx2.name2Index("y"));
		assertEquals(1, ctx2.name2Index("x"));
		assertTrue(ctx2.isNameBound("y"));
		assertTrue(ctx2.isNameBound("x"));
		assertFalse(ctx2.isNameBound("z"));
		assertEquals("x_", ctx2.pickFreshName("x")._2);
		assertEquals(3, ctx2.pickFreshName("x")._1.length());
		assertEquals("z", ctx2.pickFreshName("z")._2);
		assertEquals(3, ctx2.pickFreshName("z")._1.length());
	}

	@Test
	public void testShift() {
		TermAlgTermElement x2 = fact.TmVar(1, 2);
		TermAlgTermElement y = fact.TmVar(0, 2);
		assertEquals("if x then y else if y then x else x", eval1.termShift(0, fact.TmIf(x2, y, fact.TmIf(y, x2, x2))).accept(print).apply(ctx2));
		assertEquals("x", eval1.termShift(1, x).accept(print).apply(ctx2));

		// (\.\.1 (0 2)) -> (\.\.1 (0 4))
		TermAlgTermElement e = fact.TmAbs("x", fact.TmAbs("y", fact.TmApp(fact.TmVar(1, 3), fact.TmApp(fact.TmVar(0, 3), fact.TmVar(2, 3)))));
		assertEquals("\\x.\\y.[bad index: 1/5 in {(y,), (x,)}] [bad index: 0/5 in {(y,), (x,)}] [bad index: 4/5 in {(y,), (x,)}]", eval1.termShift(2, e).accept(print).apply(ctx));
	}

	// Exercise 6.2.5
	@Test
	public void testTermSubst() {
		TermAlgTermElement e;

		e = fact.TmApp(fact.TmVar(0, 2), fact.TmVar(0, 2));
		assertEquals("b b", e.accept(print).apply(ctx.addName("a").addName("b")));
		assertEquals("a a", eval1.termSubst(0, fact.TmVar(1, 2), e).accept(print).apply(ctx.addName("a").addName("b")));

		e = fact.TmApp(fact.TmVar(0, 2), fact.TmAbs("x", fact.TmAbs("y", fact.TmVar(2, 4))));
		// [b -> a] (b \.x\.y b) = a (\.x\.y a)
		assertEquals("b \\x.\\y.b", e.accept(print).apply(ctx.addName("a").addName("b")));
		assertEquals("a \\x.\\y.a", eval1.termSubst(0, fact.TmVar(1, 2), e).accept(print).apply(ctx.addName("a").addName("b")));

		// [b -> a (\z.a)] (b (\x.b)) = (a (\z.a)) (\x.(a (\z.a)))
		e = fact.TmApp(fact.TmVar(0, 2), fact.TmAbs("x", fact.TmVar(1, 3)));
		assertEquals("b \\x.b", e.accept(print).apply(ctx.addName("a").addName("b")));
		assertEquals("a \\z.a \\x.a \\z.a", eval1.termSubst(0, fact.TmApp(fact.TmVar(1, 2), fact.TmAbs("z", fact.TmVar(2, 3))), e).accept(print).apply(ctx.addName("a").addName("b")));

		// [b -> a] (\b. b a) = (\.b b a)
		assertEquals("\\b.b a", fact.TmAbs("b", fact.TmApp(fact.TmVar(0, 2), fact.TmVar(1, 2))).accept(print).apply(ctx.addName("a")));
		assertEquals("\\b_.b_ a", eval1.termSubst(0, fact.TmVar(1, 2), fact.TmAbs("b", fact.TmApp(fact.TmVar(0, 3), fact.TmVar(2, 3)))).accept(print).apply(ctx.addName("a").addName("b")));

		// [b -> a] (\a. b a) = (\a_. a a_)
		assertEquals("\\a.b a", fact.TmAbs("a", fact.TmApp(fact.TmVar(1, 2), fact.TmVar(0, 2))).accept(print).apply(ctx.addName("b")));
		assertEquals("\\a_.a a_", eval1.termSubst(0, fact.TmVar(1, 2), fact.TmAbs("a", fact.TmApp(fact.TmVar(1, 3), fact.TmVar(0, 3)))).accept(print).apply(ctx.addName("a").addName("b")));
	}
}
