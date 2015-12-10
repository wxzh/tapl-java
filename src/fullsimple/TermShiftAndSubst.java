package fullsimple;

import library.Tuple3;

public interface TermShiftAndSubst<Term, Ty> {
	TmMap<Term, Ty> tmMap();
	TypeShiftAndSubst<Ty> typeShiftAndSubst();
	fullsimple.termalg.shared.TermAlg<Term, Ty, Term> alg();

	default Term termShiftAbove(int d, int c, Term t) {
		return tmMap().visitTerm(t)
				.apply(new Tuple3<>((c1, x, n) -> x >= c1 ? alg().TmVar(x + d, n + d) : alg().TmVar(x, n + d), (c1, ty) -> typeShiftAndSubst().typeShiftAbove(d, c1, ty), c));
	}

	default Term termShift(int d, Term t) {
		return termShiftAbove(d, 0, t);
	}

	// [j -> s] in t
	default Term termSubst(int j, Term s, Term t) {
		return tmMap().visitTerm(t)
				.apply(new Tuple3<>((c, x, n) -> x == c ? termShift(c, s) : alg().TmVar(x, n), (c, ty) -> ty, j));
	}

	default Term termSubstTop(Term s, Term t) {
		return termShift(-1, termSubst(0, termShift(1, s), t));
	}

	default Term tyTermSubstTop(Ty ty, int j, Term t) {
		return tmMap().visitTerm(t).apply(new Tuple3<>((c, x, n) -> alg().TmVar(x, n), (c, tyT) -> typeShiftAndSubst().typeSubst(c, ty, tyT), j));
	}

}
