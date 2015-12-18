package fullsimple;

public interface TermShiftAndSubst<Term, Ty> extends variant.TermShiftAndSubst<Term, Ty>{
	@Override
	TmMap<Term, Ty> tmMap();
}
