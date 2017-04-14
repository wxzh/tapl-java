package extension;

import annotation.Visitor;

@Visitor public interface TyAlg<Ty> extends tyarith.TyAlg<Ty>, record.TyAlg<Ty>, literal.TyAlg<Ty> {}