package utils;

import annotation.Free;

@Free
public interface VarAlg<Term> {
	Term TmVar(int x, int n); // x: de Bruijn index(> 0, the xth binder starting from the innermost); n: # of vars in the context
}
