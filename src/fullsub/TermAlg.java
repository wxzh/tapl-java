package fullsub;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty> extends typed.TermAlg<Term, Ty>, moreextension.TermAlg<Term, Ty> {
}