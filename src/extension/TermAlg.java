package extension;

import annotation.Free;

@Free
public interface TermAlg<Term> extends arith.TermAlg<Term>, record.TermAlg<Term> {
	Term TmFloat(float f);
	Term TmTimesFloat(Term t1, Term t2);
	Term TmString(String s);
}
