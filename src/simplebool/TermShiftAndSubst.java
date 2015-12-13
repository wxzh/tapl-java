package simplebool;

public interface TermShiftAndSubst<Term, Ty> extends utils.TermShiftAndSubst<Term> {
	@Override
	TmMap<Term, Ty> tmMap();
}
