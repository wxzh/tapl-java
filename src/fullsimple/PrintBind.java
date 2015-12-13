package fullsimple;

import java.util.Optional;
import java.util.function.Function;

import fullsimple.bindingalg.shared.BindingAlgQuery;
import utils.Context;

public interface PrintBind<Bind, Term, Ty> extends BindingAlgQuery<Bind, Term, Ty, Function<Context<Bind>, String>>, simplebool.PrintBind<Bind, Ty> {
	Print<Term, Ty, Bind> printTerm();

	@Override
	PrintTy<Ty, Bind> printTy();

	default Function<Context<Bind>, String> TmAbbBind(Term t, Optional<Ty> tyOpt) {
		return ctx -> "= " + printTerm().visitTerm(t).apply(ctx) + tyOpt.map(ty -> ": " + printTy().visitTy(ty).apply(ctx)).orElse("");
	}

	default Function<Context<Bind>, String> TyAbbBind(Ty ty) {
		return ctx -> "= " + printTy().visitTy(ty).apply(ctx);
	}
}
