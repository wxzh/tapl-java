package fullsimple;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends simplebool.TyAlg<Ty>, tyarith.TyAlg<Ty>, record.TyAlg<Ty>, variant.TyAlg<Ty> {
	Ty TyFloat();
	Ty TyId(String b);
	Ty TyUnit();
	Ty TyString();
}