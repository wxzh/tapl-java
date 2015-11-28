package arith;

import annotation.Free;

@Free
public interface TermAlg<Term> extends BoolAlg<Term>, NatAlg<Term> {
	Term TmIsZero(Term t);
}