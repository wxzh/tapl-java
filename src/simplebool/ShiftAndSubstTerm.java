package simplebool;

public interface ShiftAndSubstTerm<Term, Ty> extends utils.ShiftAndSubstTerm<Term> {
	TmMap<Term, Ty> tmMap();
}
