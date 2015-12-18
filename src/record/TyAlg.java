package record;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TyAlg<Ty> extends typed.TyAlg<Ty> {
	Ty TyRecord(List<Tuple2<String, Ty>> els);
}
