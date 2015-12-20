package fullsimple;

import fullsimple.termalg.shared.G_TermAlgTransform;
import varapp.TmMapCtx;

public interface TmMap<Term, Ty> extends G_TermAlgTransform<TmMapCtx<Term>, Term, Ty>, variant.TmMap<Term, Ty>, moreextension.TmMap<Term> {
}