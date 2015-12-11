package fullsimple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

import org.junit.Test;

import fullsimple.TyEqv.TyEqual;
import fullsimple.bindingalg.external.BindVisitor;
import fullsimple.bindingalg.external.BindingAlgFactory;
import fullsimple.bindingalg.external.BindingAlgMatcher;
import fullsimple.bindingalg.external.BindingAlgMatcherImpl;
import fullsimple.bindingalg.external.IBind;
import fullsimple.termalg.external.ITerm;
import fullsimple.termalg.external.TermAlgFactory;
import fullsimple.termalg.external.TermAlgMatcherImpl;
import fullsimple.termalg.external.TermVisitor;
import fullsimple.tyalg.external.ITy;
import fullsimple.tyalg.external.TyAlgFactory;
import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.external.TyAlgMatcherImpl;
import fullsimple.tyalg.external.TyVisitor;
import fullsimple.tyalg.shared.TyAlg;
import library.Tuple2;
import library.Tuple3;
import utils.Context;

public class Tests {
	class PrintImpl implements Print<ITerm<ITy>, ITy, IBind<ITerm<ITy>, ITy>>,
			TermVisitor<Function<Context<IBind<ITerm<ITy>, ITy>>, String>, ITy> {

		@Override
		public PrintBind<IBind<ITerm<ITy>, ITy>, ITerm<ITy>, ITy> printBind() {
			return printBind;
		}

		@Override
		public PrintTy<ITy, IBind<ITerm<ITy>, ITy>> printTy() {
			return printTy;
		}

		@Override
		public fullsimple.termalg.external.TermAlgMatcher<ITerm<ITy>, ITy, String> matcher() {
			return new TermAlgMatcherImpl<>();
		}
	}

	class PrintTyImpl implements PrintTy<ITy, IBind<ITerm<ITy>, ITy>>,
			TyVisitor<Function<Context<IBind<ITerm<ITy>, ITy>>, String>> {

		@Override
		public TyAlgMatcher<ITy, String> matcher() {
			return new TyAlgMatcherImpl<>();
		}
	}

	class PrintBindImpl implements PrintBind<IBind<ITerm<ITy>, ITy>, ITerm<ITy>, ITy>,
			BindVisitor<Function<Context<IBind<ITerm<ITy>, ITy>>, String>, ITerm<ITy>, ITy> {

		@Override
		public PrintTy<ITy, IBind<ITerm<ITy>, ITy>> printTy() {
			return printTy;
		}

		@Override
		public Print<ITerm<ITy>, ITy, IBind<ITerm<ITy>, ITy>> printTerm() {
			return printTerm;
		}
	}

	class SimplifyTyImpl implements SimplifyTy<ITy, IBind<ITerm<ITy>, ITy>, ITerm<ITy>>,
			TyVisitor<Function<Context<IBind<ITerm<ITy>, ITy>>, ITy>> {
		@Override
		public BindingAlgMatcher<IBind<ITerm<ITy>, ITy>, ITerm<ITy>, ITy, ITy> bindMatcher() {
			return new BindingAlgMatcherImpl<>();
		}

		@Override
		public TyAlg<ITy, ITy> alg() {
			return tyFact;
		}
	}

	class GetTypeFromBindImpl
			implements GetTypeFromBind<IBind<ITerm<ITy>, ITy>, ITerm<ITy>, ITy>, BindVisitor<ITy, ITerm<ITy>, ITy> {
	}

	class TyMapImpl implements TyMap<ITy>, TyVisitor<Function<Tuple2<utils.TyMap.VarMapper<ITy>, Integer>, ITy>> {
		@Override
		public TyAlg<ITy, ITy> alg() {
			return tyFact;
		}
	}

	class TyEqualImpl implements TyEqual<ITy, IBind<ITerm<ITy>, ITy>>, TyVisitor<Function<ITy, Boolean>> {
		@Override
		public TyAlgMatcher<ITy, Boolean> matcher() {
			return new TyAlgMatcherImpl<>();
		}
	}

	class TypeofImpl implements Typeof<ITerm<ITy>, ITy, IBind<ITerm<ITy>, ITy>>,
			TermVisitor<Function<Context<IBind<ITerm<ITy>, ITy>>, ITy>, ITy> {

		@Override
		public SimplifyTy<ITy, IBind<ITerm<ITy>, ITy>, ITerm<ITy>> simplifyTy() {
			return new SimplifyTyImpl();
		}

		@Override
		public TyEqv.TyEqual<ITy, IBind<ITerm<ITy>, ITy>> tyEqual() {
			return new TyEqualImpl();
		}

		@Override
		public TyAlgMatcher<ITy, ITy> tyMatcher() {
			return new TyAlgMatcherImpl<>();
		}

		@Override
		public TyAlg<ITy, ITy> tyAlg() {
			return tyFact;
		}

		@Override
		public TyMap<ITy> tyMap() {
			return new TyMapImpl();
		}

		@Override
		public fullsimple.bindingalg.shared.BindingAlg<IBind<ITerm<ITy>, ITy>, ITerm<ITy>, ITy, IBind<ITerm<ITy>, ITy>> bindAlg() {
			return bindFact;
		}

		@Override
		public simplebool.GetTypeFromBind<IBind<ITerm<ITy>, ITy>, ITy> getTypeFromBind() {
			return new GetTypeFromBindImpl();
		}
	}

	// printers
	PrintTyImpl printTy = new PrintTyImpl();
	PrintImpl printTerm = new PrintImpl();
	PrintBindImpl printBind = new PrintBindImpl();

	// elements
	ITy ty;
	IBind<ITerm<ITy>, ITy> bind;
	ITerm<ITy> term;

	// factories
	TyAlgFactory tyFact = new TyAlgFactory();
	BindingAlgFactory<ITerm<ITy>, ITy> bindFact = new BindingAlgFactory<>();
	TermAlgFactory<ITy> termFact = new TermAlgFactory<>();

	Context<IBind<ITerm<ITy>, ITy>> ctx = new Context<IBind<ITerm<ITy>, ITy>>(bindFact);

	ITy bool = tyFact.TyBool();
	ITerm<ITy> t = termFact.TmTrue();
	ITerm<ITy> x = termFact.TmVar(0, 1);

	// typer
	TypeofImpl typeof = new TypeofImpl();
	TyEqualImpl tyEqual = new TyEqualImpl();


	@Test
	public void testPrintTyFloat() {
		ty = tyFact.TyFloat();
		assertEquals("Float", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintTyId() {
		ty = tyFact.TyId("Type");
		assertEquals("Type", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintTyUnit() {
		ty = tyFact.TyUnit();
		assertEquals("Unit", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintTyRecord() {
		ty = tyFact.TyRecord(Arrays.asList(new Tuple2<>("bool", tyFact.TyBool()), new Tuple2<>("nat", tyFact.TyNat())));
		assertEquals("{bool:Bool,nat:Nat}", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintTyVariant() {
		ty = tyFact
				.TyVariant(Arrays.asList(new Tuple2<>("bool", tyFact.TyBool()), new Tuple2<>("nat", tyFact.TyNat())));
		assertEquals("<bool:Bool,nat:Nat>", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintTyArr() {
		ty = tyFact.TyArr(tyFact.TyString(), tyFact.TyArr(tyFact.TyNat(), tyFact.TyBool()));
		assertEquals("(String -> (Nat -> Bool))", ty.accept(printTy).apply(ctx));
	}

	@Test
	public void testPrintVarBind() {
		bind = bindFact.VarBind(bool);
		assertEquals(": Bool", bind.accept(printBind).apply(ctx));
	}

	@Test
	public void testPrintTyTvarBind() {
		bind = bindFact.TyTvarBind();
		assertEquals("", bind.accept(printBind).apply(ctx));
	}

	@Test
	public void testPrintTyAbbBind() {
		bind = bindFact.TyAbbBind(bool);
		assertEquals("= Bool", bind.accept(printBind).apply(ctx));
	}

	@Test
	public void testPrintTmAbbBind() {
		bind = bindFact.TmAbbBind(t, Optional.of(tyFact.TyBool()));
		assertEquals("= true: Bool", bind.accept(printBind).apply(ctx));
		bind = bindFact.TmAbbBind(t, Optional.empty());
		assertEquals("= true", bind.accept(printBind).apply(ctx));
	}

	@Test
	public void testPrintTmUnit() {
		term = termFact.TmUnit();
		assertEquals("Unit", term.accept(printTerm).apply(ctx));
	}

	@Test
	public void testPrintTmInert() {
		term = termFact.TmInert(bool);
		assertEquals("inert[Bool]", term.accept(printTerm).apply(ctx));
	}

	@Test
	public void testPrintTmFix() {
		term = termFact.TmFix(t);
		assertEquals("fix true", term.accept(printTerm).apply(ctx));
	}

	@Test
	public void testPrintTmTag() {
		term = termFact.TmTag("x", t, bool);
		assertEquals("<x=true> as Bool", term.accept(printTerm).apply(ctx));
	}

	@Test
	public void testPrintTmCase() {
		term = termFact.TmCase(t, Arrays.asList(new Tuple3<>("X", "x", termFact.TmVar(0, 2)),
				new Tuple3<>("Y", "y", termFact.TmVar(0, 2))));
		assertEquals("case true of <X=x>==>x| <Y=y_>==>y_", term.accept(printTerm).apply(ctx.addName("y")));
	}

	@Test
	public void testPrintLet() {
		term = termFact.TmLet("x", t, x);
		assertEquals("let x=true in x", term.accept(printTerm).apply(ctx));
	}

	@Test
	public void testTypeofTmTrue() {
		ty = t.accept(typeof).apply(ctx);
		assertTrue(bool.accept(tyEqual).apply(ty));
	}

	@Test
	public void testTypeofTmAscribe() {
		ty = tyFact.TyUnit();
		term = termFact.TmAscribe(termFact.TmUnit(), ty);
		assertTrue(ty.accept(tyEqual).apply(term.accept(typeof).apply(ctx)));
		assertFalse(bool.accept(tyEqual).apply(term.accept(typeof).apply(ctx)));
	}

	@Test
	public void testTypeofTmTag() {
		ty = tyFact.TyUnit();
		term = termFact.TmAscribe(termFact.TmUnit(), ty);
		assertTrue(ty.accept(tyEqual).apply(term.accept(typeof).apply(ctx)));
		assertFalse(bool.accept(tyEqual).apply(term.accept(typeof).apply(ctx)));
	}
}