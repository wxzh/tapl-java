package fullerror;

import fullerror.termalg.shared.G_TermAlgTransform;
import varapp.TmMapCtx;

public interface TmMap<Term, Ty> extends
		G_TermAlgTransform<TmMapCtx<Term>, Term, Ty>, simplebool.TmMap<Term, Ty>{
}
