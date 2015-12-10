package utils;

import annotation.Free;

@Free
public interface TyVarAlg<Ty> {
	Ty TyVar(int x, int n);
}
