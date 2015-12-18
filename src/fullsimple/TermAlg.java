package fullsimple;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty>
		extends variant.TermAlg<Term, Ty>, extension.TermAlg<Term> {
	Term TmFix(Term t);

	Term TmUnit();

	Term TmAscribe(Term t, Ty ty);

	Term TmInert(Ty ty);

	Term TmLet(String x, Term t1, Term t2);
}
