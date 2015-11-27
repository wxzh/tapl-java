package arith;

public interface IsVal<I> extends IsNumericalVal<I> {
	@Override
	default Boolean TmTrue() {
		return true;
	}

	@Override
	default Boolean TmFalse() {
		return true;
	}
}