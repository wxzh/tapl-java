package arith;

import annotation.Free;

@Free
public interface TermAlg<Term> {
	Term TmTrue();
	Term TmFalse();
	Term TmIf(Term cond, Term t1, Term t2);
	Term TmZero();
	Term TmSucc(Term t);
	Term TmPred(Term t);
	Term TmIsZero(Term t);
}