package fulluntyped;

import annotation.Free;

@Free
public interface TermAlg<Term> extends untyped.TermAlg<Term>, extension.TermAlg<Term> {
}