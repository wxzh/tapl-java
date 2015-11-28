package arith;

import annotation.Free;

@Free
public interface BoolAlg<Term> {
	Term TmTrue();
	Term TmFalse();
	Term TmIf(Term t1, Term t2, Term t3);
}
