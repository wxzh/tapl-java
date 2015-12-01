package tyarith;

import arith.termalg.shared.TermAlgQuery;
import library.Zero;
import tyarith.tyalg.external.TyAlgMatcher;
import utils.ZeroTypeError;

interface Typeof<Term, Ty> extends TermAlgQuery<Term, Ty> {
	tyarith.tyalg.shared.TyAlg<Ty, Ty> ty();
	TyAlgMatcher<Ty, Ty> matcher();
	TEqual<Ty> tEqual(Ty other);

	@Override
	default Zero<Ty> m() {
		return new ZeroTypeError<>();
	}

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
				.TyBool(() -> tEqual(visitTerm(t2)).visitTy(visitTerm(t1)) ? visitTerm(t2) : m().empty())
				.otherwise(() -> m().empty())
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
				.otherwise(() -> m().empty())
				.visitTy(visitTerm(t));
	}

	@Override
	default Ty TmPred(Term t) {
		return matcher()
				.TyNat(() -> ty().TyNat())
				.otherwise(() -> m().empty())
				.visitTy(visitTerm(t));
	}

	@Override
	default Ty TmIsZero(Term t) {
		return matcher()
				.TyNat(() -> ty().TyBool())
				.otherwise(() -> m().empty())
				.visitTy(visitTerm(t));
	}
}