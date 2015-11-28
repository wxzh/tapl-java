package arith;

import annotation.Free;

@Free
public interface NatAlg<Term> {
	Term TmZero();
	Term TmSucc(Term t);
	Term TmPred(Term t);
}
