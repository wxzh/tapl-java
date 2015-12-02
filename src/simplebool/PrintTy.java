package simplebool;

import tyarith.tyalg.external.TyAlgMatcher;

public interface PrintTy<Ty> extends simplebool.tyalg.shared.TyAlg<Ty, String> {
	TyAlgMatcher<Ty, String> matcher();

	default String TyBool() {
		return "Bool";
	}

	default String TyArr(Ty t1, Ty t2) {
		return printTyWithParensIfNonAtomic(t1) + " -> " + visitTy(t2);
	}

	default String printTyWithParensIfNonAtomic(Ty t) {
		return matcher()
				.TyBool(() -> visitTy(t))
				.otherwise(() -> "(" + visitTy(t) + ")")
				.visitTy(t);
	}
}