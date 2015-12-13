package fulluntyped;

import annotation.Free;

@Free
public interface TermAlg<Term> extends untyped.TermAlg<Term>, ExtAlg<Term> {
	Term TmLet(String x, Term t1, Term t2);
}
