package tyarith;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends utils.TyAlg<Ty> {
	Ty TyNat();
}