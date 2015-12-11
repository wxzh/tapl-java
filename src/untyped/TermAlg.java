package untyped;

import annotation.Free;
import utils.VarAppAlg;

@Free
public interface TermAlg<Term> extends VarAppAlg<Term> {
	Term TmAbs(String x, Term t);
}