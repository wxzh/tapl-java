package untyped;

import annotation.Free;
import utils.VarAlg;

@Free
public interface TermAlg<Term> extends VarAlg<Term> {
	Term TmAbs(String x, Term t);
	Term TmApp(Term t1, Term t2);
}