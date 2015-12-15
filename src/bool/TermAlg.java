package bool;

import annotation.Free;

@Free
public interface TermAlg<Term> {
	Term TmTrue();
	Term TmFalse();
	Term TmIf(Term t1, Term t2, Term t3);
}
