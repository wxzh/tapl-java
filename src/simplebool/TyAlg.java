package simplebool;

import annotation.Free;

@Free
public interface TyAlg<Ty> {
	Ty TyArr(Ty t1, Ty t2);
	Ty TyBool();
}

