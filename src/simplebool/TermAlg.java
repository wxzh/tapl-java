package simplebool;

import annotation.Free;
import arith.BoolAlg;

@Free
public interface TermAlg<Term, Ty> extends BoolAlg<Term> {
	Term TmVar(int n, int x);
	Term TmAbs(String x, Ty t, Term e);
	Term TmApp(Term e1, Term e2);
}
