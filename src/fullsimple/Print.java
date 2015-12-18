package fullsimple;

import java.util.function.Function;

import fullsimple.termalg.external.TermAlgMatcher;
import fullsimple.termalg.shared.GTermAlg;
import fulluntyped.PrintExt;
import utils.Context;

public interface Print<Term, Ty, Bind>
		extends GTermAlg<Term, Ty, Function<Context<Bind>, String>>, simplebool.Print<Term, Ty, Bind>, variant.Print<Term, Ty, Bind>, PrintExt<Term, Bind> {
	@Override
	TermAlgMatcher<Term, Ty, String> matcher();
	@Override
	PrintTy<Ty, Bind> printTy();

	@Override
	default Function<Context<Bind>, String> TmInert(Ty ty) {
		return ctx -> "inert[" + printTy().visitTy(ty).apply(ctx) + "]";
	}

	@Override
	default Function<Context<Bind>, String> TmFix(Term t) {
		return ctx -> "fix " + visitTerm(t).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmAscribe(Term t, Ty ty) {
		return ctx -> visitTerm(t).apply(ctx) + " as " + printTy().visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TmUnit() {
		return ctx -> "Unit";
	}

	default Function<utils.Context<Bind>,String> TmLet(String x, Term t1, Term t2) {
		return ctx -> "let " + x + "=" + visitTerm(t1).apply(ctx) + " in " + visitTerm(t2).apply(ctx.addName(x));
	}
}