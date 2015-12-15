package fullsimple;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TyAlg<Ty> extends simplebool.TyAlg<Ty>, tyarith.TyAlg<Ty>, record.TyAlg<Ty> {
	Ty TyFloat();
	Ty TyId(String b);
	Ty TyUnit();
	Ty TyVariant(List<Tuple2<String, Ty>> els);
	Ty TyString();
}