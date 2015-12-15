package arith;

import annotation.Free;

@Free
public interface TermAlg<Term> extends bool.TermAlg<Term>, nat.TermAlg<Term> {
	Term TmIsZero(Term t);
}