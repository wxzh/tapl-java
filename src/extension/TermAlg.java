package extension;

import annotation.Visitor;

@Visitor public interface TermAlg<Term> extends arith.TermAlg<Term>, record.TermAlg<Term>, literal.TermAlg<Term>, let.TermAlg<Term> {}