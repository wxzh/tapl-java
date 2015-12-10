package untyped;


public interface TermShiftAndSubst<Term> extends utils.TermShiftAndSubst<Term> {
	TmMap<Term> tmMap();
}
