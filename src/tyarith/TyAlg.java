package tyarith;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends bool.TyAlg<Ty>, nat.TyAlg<Ty> {
	Ty TyNat();
}