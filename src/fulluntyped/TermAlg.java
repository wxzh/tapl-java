package fulluntyped;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TermAlg<Term> extends arith.TermAlg<Term>, untyped.TermAlg<Term> {
	Term TmRecord(List<Tuple2<String, Term>> fields);
	Term TmProj(Term t, String l);
	Term TmFloat(float f);
	Term TmTimesFloat(Term t1, Term t2);
	Term TmString(String s);
	Term TmLet(String x, Term t1, Term t2);
}
