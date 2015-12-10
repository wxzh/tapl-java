package simplebool;

public interface TermShiftAndSubst<Term, Ty> extends utils.TermShiftAndSubst<Term> {
	TmMap<Term, Ty> tmMap();
}
