package fullsimple;

import java.util.Optional;

import annotation.Free;

@Free
public interface BindingAlg<Bind, Term, Ty> extends typed.BindingAlg<Bind, Ty> {
	Bind TmAbbBind(Term t, Optional<Ty> ty);
	Bind TyAbbBind(Ty ty);
}