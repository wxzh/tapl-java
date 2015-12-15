package rcdsubbot;

import annotation.Free;

@Free
public interface TermAlg<Term, Ty> extends typed.TermAlg<Term, Ty>, record.TermAlg<Term> {
}