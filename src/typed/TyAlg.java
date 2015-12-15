package typed;

import annotation.Free;

@Free
public interface TyAlg<Ty> {
	Ty TyArr(Ty ty1, Ty ty2);
}

