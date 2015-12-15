package typed;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty> extends varapp.TermAlg<Term> {
	Term TmAbs(String x, Ty ty, Term t);
}
