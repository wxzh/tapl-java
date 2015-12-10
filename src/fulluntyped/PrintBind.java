package fulluntyped;

import java.util.function.Function;

import fulluntyped.bindingalg.shared.BindingAlgQuery;
import utils.Context;

public interface PrintBind<Bind, Term> extends BindingAlgQuery<Bind, Term, Function<Context<Bind>, String>>, untyped.PrintBind<Bind>{
	Print<Term, Bind> printTerm();
	default Function<Context<Bind>, String> TmAbbBind(Term t) {
		return ctx -> printTerm().visitTerm(t).apply(ctx);
	}
}
