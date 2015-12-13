package untyped;

public interface TermShiftAndSubst<Term> extends utils.TermShiftAndSubst<Term> {
	@Override
	TmMap<Term> tmMap();
}
