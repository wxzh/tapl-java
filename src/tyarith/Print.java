package tyarith;

public interface Print<Ty> extends tyarith.tyalg.shared.TyAlg<Ty, String> {
	default String TyBool() {
		return "Bool";
	}

	default String TyNat() {
		return "Nat";
	}
}