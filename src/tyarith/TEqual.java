package tyarith;

import tyarith.tyalg.external.TyAlgMatcher;

public interface TEqual<I> extends tyarith.tyalg.shared.TyAlg<I, Boolean> {
	I other();
	TyAlgMatcher<I, Boolean> matcher();

	@Override
	default Boolean TyBool() {
		return matcher()
				.TyBool(() -> true)
				.otherwise(() -> false)
				.visitTy(other());
	}


	@Override
	default Boolean TyNat() {
		return matcher()
				.TyNat(() -> true)
				.otherwise(() -> false)
				.visitTy(other());
	}
}