package variant;

import varapp.TmMapCtx;
import variant.termalg.shared.G_TermAlgTransform;

public interface TmMap<Term, Ty> extends G_TermAlgTransform<TmMapCtx<Term>, Term, Ty>, typed.TmMap<Term, Ty> {
}