package extension;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends tyarith.TyAlg<Ty>, record.TyAlg<Ty> {
	Ty TyFloat();
	Ty TyString();
}