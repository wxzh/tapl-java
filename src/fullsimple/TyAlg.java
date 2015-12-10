package fullsimple;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TyAlg<Ty> extends utils.TyVarAlg<Ty>, simplebool.TyAlg<Ty>, tyarith.TyAlg<Ty> {
	Ty TyFloat();
	Ty TyId(String b);
	Ty TyUnit();
	Ty TyRecord(List<Tuple2<String, Ty>> els);
	Ty TyVariant(List<Tuple2<String, Ty>> els);
	Ty TyString();
}