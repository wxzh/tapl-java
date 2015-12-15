package simplebool;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty> extends typed.TermAlg<Term, Ty>, bool.TermAlg<Term> {
}
