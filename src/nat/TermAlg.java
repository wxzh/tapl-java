package nat;

import annotation.Free;

@Free
public interface TermAlg<Term> {
	Term TmZero();
	Term TmSucc(Term t);
	Term TmPred(Term t);
}
