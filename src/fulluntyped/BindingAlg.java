package fulluntyped;

import annotation.Free;

@Free
public interface BindingAlg<Bind, Term> extends utils.BindingAlg<Bind> {
	Bind TmAbbBind(Term t);
}
