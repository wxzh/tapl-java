package fullsub;

import fullsub.termalg.shared.G_TermAlgTransform;
import varapp.TmMapCtx;

public interface TmMap<Term, Ty> extends G_TermAlgTransform<TmMapCtx<Term>, Term, Ty>, moreextension.TmMap<Term>, typed.TmMap<Term, Ty> {
}
