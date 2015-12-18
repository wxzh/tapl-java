package variant;

import java.util.List;

import annotation.Free;
import library.Tuple3;

@Free
public interface TermAlg<Term, Ty> extends typed.TermAlg<Term, Ty> {
	Term TmCase(Term t, List<Tuple3<String, String, Term>> alts);
	Term TmTag(String x, Term t, Ty ty);
}
