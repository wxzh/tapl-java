package untyped;

import static org.junit.Assert.assertEquals;

import java.util.function.Function;

import org.junit.Test;

import library.Tuple2;
import untyped.bindingalg.external.BindingAlgBindElement;
import untyped.bindingalg.external.BindingAlgBindVisitor;
import untyped.bindingalg.external.BindingAlgFactory;
import untyped.termalg.external.TermAlgFactory;
import untyped.termalg.external.TermAlgMatcher;
import untyped.termalg.external.TermAlgMatcherImpl;
import untyped.termalg.external.TermAlgTermElement;
import untyped.termalg.external.TermAlgTermVisitor;
import untyped.termalg.shared.TermAlg;

public class Tests {

	class TmMapImpl implements TmMap<TermAlgTermElement>,
			TermAlgTermVisitor<Function<Tuple2<TmMap.VarMapper<TermAlgTermElement>, Integer>, TermAlgTermElement>> {
		public TermAlg<TermAlgTermElement, TermAlgTermElement> alg() {
			return fact;
		}
	}

	class IsValImpl implements IsVal<TermAlgTermElement>, TermAlgTermVisitor<Boolean> {
	}

	class Eval1Impl implements Eval1<TermAlgTermElement>, TermAlgTermVisitor<TermAlgTermElement> {
		@Override
		public TmMap<TermAlgTermElement> tmMap() {
			return new TmMapImpl();
		}

		@Override
		public IsVal<TermAlgTermElement> isVal() {
			return isVal;
		}

		public TermAlg<TermAlgTermElement, TermAlgTermElement> alg() {
			return fact;
		}

		public TermAlgMatcher<TermAlgTermElement, TermAlgTermElement> matcher() {
			return new TermAlgMatcherImpl<>();
		}
	}

	class PrintImpl implements Print<TermAlgTermElement, BindingAlgBindElement>,
			TermAlgTermVisitor<Function<Context<BindingAlgBindElement>, String>> {
	}

	class PrintBindImpl implements PrintBind<BindingAlgBindElement>,
			BindingAlgBindVisitor<String> {
	}

	IsVal<TermAlgTermElement> isVal = new IsValImpl();
	PrintImpl print = new PrintImpl();
	TermAlgFactory fact = new TermAlgFactory();
	BindingAlgFactory bindFact = new BindingAlgFactory();
	Context<BindingAlgBindElement> ctx = new Context<BindingAlgBindElement>(bindFact, new PrintBindImpl());

	@Test
	public void testPrint() {
		assertEquals("x", fact.TmVar(0, 1).accept(print).apply(ctx.addName("x")));
		assertEquals("\\x_.x_ \\y.y", fact.TmApp(fact.TmAbs("x", fact.TmVar(0, 2)), fact.TmAbs("y", fact.TmVar(0, 2)))
				.accept(print).apply(ctx.addName("x")));
		assertEquals("\\a.a x",
				fact.TmApp(fact.TmAbs("a", fact.TmVar(0, 2)), fact.TmVar(0, 1)).accept(print).apply(ctx.addName("x")));
	}

	@Test
	public void eval1Test() {

	}

}
// x/;
// (x) x x;
// (\a. a) x;
// \a.\ b. a b;
// \a.\b. b a;
// (\a.\b. b a) x x;
// (\y. y) (\z. z);