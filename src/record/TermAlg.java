package record;

import java.util.List;

import annotation.Free;
import library.Tuple2;

@Free
public interface TermAlg<Term> {
	Term TmRecord(List<Tuple2<String, Term>> fields);
	Term TmProj(Term t, String l);
}
