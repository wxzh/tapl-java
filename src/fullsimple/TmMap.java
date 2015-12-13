package fullsimple;

import fullsimple.termalg.shared.G_TermAlgTransform;
import library.Tuple2;
import utils.TmMap.VarMapper;

public interface TmMap<Term, Ty> extends
		G_TermAlgTransform<Tuple2<VarMapper<Term>, Integer>, Term, Ty>, simplebool.TmMap<Term, Ty> {
}
