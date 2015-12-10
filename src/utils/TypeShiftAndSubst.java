package utils;

import library.Tuple2;

public interface TypeShiftAndSubst<Ty> {
	TyMap<Ty> tyMap();
	utils.tyvaralg.shared.TyVarAlg<Ty, Ty> tyAlg();

	default Ty typeShiftAbove(int d, int c, Ty ty) {
		return tyMap().visitTy(ty).apply(new Tuple2<>((c1, x, n) -> x >= c1 ? tyAlg().TyVar(x+d, n+d) : tyAlg().TyVar(x, n+d), c));
	}

	default Ty typeShift(int d, Ty ty) {
		return typeShiftAbove(d, 0, ty);
	}

	default Ty typeSubst(int j, Ty tyS, Ty tyT) {
		return tyMap().visitTy(tyT)
				.apply(new Tuple2<>((c, x, n) -> x == c ? typeShift(c, tyS) : tyAlg().TyVar(x, n), j));
	}

	default Ty typeSubstTop(Ty s, Ty t) {
		return typeShift(-1, typeSubst(0, typeShift(1, s), t));
	}
}
