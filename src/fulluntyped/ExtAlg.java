package fulluntyped;

import java.util.List;

import annotation.Free;
import library.Tuple2;

// simple extensions
@Free
public interface ExtAlg<Term> extends arith.TermAlg<Term> {
	Term TmRecord(List<Tuple2<String, Term>> fields);
	Term TmProj(Term t, String l);
	Term TmFloat(float f);
	Term TmTimesFloat(Term t1, Term t2);
	Term TmString(String s);
}
