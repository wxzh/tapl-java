package simplebool;

import annotation.Free;
import arith.BoolAlg;
import utils.VarAppAlg;

@Free
public interface TermAlg<Term, Ty> extends VarAppAlg<Term>, BoolAlg<Term> {
	Term TmAbs(String x, Ty t, Term e);
}
