package fullsimple;

import annotation.Free;
import fulluntyped.ExtAlg;

@Free
public interface TermAlg<Term, Ty> extends arith.TermAlg<Term>, variant.TermAlg<Term, Ty>, simplebool.TermAlg<Term, Ty>, ExtAlg<Term> {
	Term TmFix(Term t);
	Term TmUnit();
	Term TmAscribe(Term t, Ty ty);
	Term TmInert(Ty ty);
	Term TmLet(String x, Term t1, Term t2);
}
