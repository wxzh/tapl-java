package moreextension;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends extension.TyAlg<Ty> {
	Ty TyUnit();
}