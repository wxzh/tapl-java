package simplebool;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends utils.TyBoolAlg<Ty> {
	Ty TyArr(Ty ty1, Ty ty2);
}

