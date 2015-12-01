package fulluntyped;

import annotation.Free;

@Free
public interface BindingAlg<Bind, Term> extends untyped.BindingAlg<Bind> {
	Bind TmAbbBind(Term t);
}
