package tyarith;

import arith.termalg.shared.TermAlgQuery;
import tyarith.tyalg.external.TyAlgMatcher;
import utils.TypeError;

interface Typeof<Term, Ty> extends TermAlgQuery<Term, Ty> {
	tyarith.tyalg.shared.TyAlg<Ty, Ty> ty();
	TyAlgMatcher<Ty, Ty> matcher();
	TEqual<Ty> tEqual(Ty other);


	@Override
	default Ty TmTrue() {
		return ty().TyBool();
	}

	@Override
	default Ty TmFalse() {
		return ty().TyBool();
	}

	@Override
	default Ty TmIf(Term t1, Term t2, Term t3) {
		return matcher()
				.TyBool(() -> {
					if (tEqual(visitTerm(t2)).visitTy(visitTerm(t1)))
						return visitTerm(t2);
					else
						throw new TypeError("arms of conditional have different tyeps");
				})
				.otherwise(() -> { throw new TypeError("guard of conditional not a boolean"); })
				.visitTy(visitTerm(t1));
	}

	@Override
	default Ty TmZero() {
		return ty().TyNat();
	}

	@Override
	default Ty TmSucc(Term t) {
		return matcher()
				.TyNat(() -> ty().TyNat())
				.otherwise(() -> { throw new TypeError("argument of succ is not a number"); })
				.visitTy(visitTerm(t));
	}

	@Override
	default Ty TmPred(Term t) {
		return matcher()
				.TyNat(() -> ty().TyNat())
				.otherwise(() -> { throw new TypeError("argument of pred is not a number"); })
				.visitTy(visitTerm(t));
	}

	@Override
	default Ty TmIsZero(Term t) {
		return matcher()
				.TyNat(() -> ty().TyBool())
				.otherwise(() -> { throw new TypeError("argument of iszero is not a number"); })
				.visitTy(visitTerm(t));
	}
}