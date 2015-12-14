package fulluntyped;

import annotation.Free;

// simple extensions
@Free
public interface ExtAlg<Term> extends arith.TermAlg<Term>, record.TermAlg<Term> {
	Term TmFloat(float f);
	Term TmTimesFloat(Term t1, Term t2);
	Term TmString(String s);
}
