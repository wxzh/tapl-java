package fulluntyped;

import fulluntyped.termalg.shared.G_TermAlgTransform;
import library.Tuple2;

public interface TmMap<Term> extends
			G_TermAlgTransform<Tuple2<utils.TmMap.VarMapper<Term>, Integer>, Term>,
		untyped.TmMap<Term> {
}
