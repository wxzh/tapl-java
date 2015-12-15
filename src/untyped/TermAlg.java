package untyped;

import annotation.Free;

@Free
public interface TermAlg<Term> extends varapp.TermAlg<Term> {
	Term TmAbs(String x, Term t);
}