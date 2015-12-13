package tyarith;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends utils.TyBoolAlg<Ty> {
	Ty TyNat();
}