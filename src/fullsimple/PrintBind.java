package fullsimple;

import java.util.Optional;
import java.util.function.Function;

import fullsimple.bindalg.shared.BindAlgQuery;
import library.Zero;
import untyped.Context;

public interface PrintBind<Bind, Term, Ty> extends BindAlgQuery<Bind, Term, Ty, Function<Context<Bind>, String>> {
	PrintTy<Ty, Bind> printTy();
	Print<Term, Ty, Bind> printTerm();

	default Zero<Function<Context<Bind>, String>> m() {
		return new Zero<Function<Context<Bind>, String>>() {
			public Function<Context<Bind>, String> empty() {
				return ctx -> "";
			}
		};
	}

	default Function<Context<Bind>, String> VarBind(Ty ty) {
		return ctx -> ": "  + printTy().visitTy(ty);
	}

	default Function<Context<Bind>, String> TmAbbBind(Term t, Optional<Ty> ty) {
		return ctx -> "= " + printTerm().visitTerm(t);
	}

	default Function<Context<Bind>, String> TyAbbBind(Ty ty) {
		return ctx -> "= " + printTy().visitTy(ty).apply(ctx);
	}
}
