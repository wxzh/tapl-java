package simplebool;

import annotation.Free;
import arith.BoolAlg;
import utils.VarAlg;

@Free
public interface TermAlg<Term, Ty> extends VarAlg<Term>, BoolAlg<Term> {
	Term TmAbs(String x, Ty t, Term e);
	Term TmApp(Term e1, Term e2);
}
