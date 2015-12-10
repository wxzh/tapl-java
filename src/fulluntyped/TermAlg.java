package fulluntyped;

import annotation.Free;

@Free
public interface TermAlg<Term> extends UntypedAlg<Term>, untyped.TermAlg<Term> {
	Term TmLet(String x, Term t1, Term t2);
}
