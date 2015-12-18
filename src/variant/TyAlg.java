package variant;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TyAlg<Ty> extends typed.TyAlg<Ty> {
	Ty TyVariant(List<Tuple2<String, Ty>> els);
}
