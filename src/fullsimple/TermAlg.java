package fullsimple;

import java.util.List;

import annotation.Free;
import fulluntyped.ExtAlg;
import library.Tuple3;

@Free
public interface TermAlg<Term, Ty> extends arith.TermAlg<Term>, simplebool.TermAlg<Term, Ty>, ExtAlg<Term> {
	Term TmCase(Term t, List<Tuple3<String, String, Term>> alts);
	Term TmTag(String x, Term t, Ty ty);
	Term TmFix(Term t);
	Term TmUnit();
	Term TmAscribe(Term t, Ty ty);
	Term TmInert(Ty ty);
	Term TmLet(String x, Term t1, Term t2);
}
