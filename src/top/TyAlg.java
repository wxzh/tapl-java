package top;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends typed.TyAlg<Ty> {
	Ty TyTop();
}
