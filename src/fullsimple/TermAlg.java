package fullsimple;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty> extends variant.TermAlg<Term, Ty>, moreextension.TermAlg<Term, Ty> {
}