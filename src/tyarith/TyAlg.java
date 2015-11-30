package tyarith;

import annotation.Free;

@Free
public interface TyAlg<Ty> {
	Ty TyBool();
	Ty TyNat();
}