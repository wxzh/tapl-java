package fulluntyped;

import untyped.Context;

public interface PrintBind<Bind, Term> extends fulluntyped.bindingalg.shared.BindingAlg<Bind, Term, String>, untyped.PrintBind<Bind> {
	Context<Bind> ctx();
	Print<Term, Bind> printTerm();
	default String TmAbbBind(Term t) {
		return printTerm().visitTerm(t).apply(ctx());
	}
}
