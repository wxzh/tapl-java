package fullsimple;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import fullsimple.termalg.shared.G_TermAlgTransform;
import library.Tuple3;
import utils.TmMap.VarMapper;

public interface TmMap<Term, Ty> extends
		G_TermAlgTransform<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term, Ty> {

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmAbs(String x, Ty ty,
			Term t) {
		return triple -> {
			VarMapper<Term> onVar = triple._1;
			BiFunction<Integer, Ty, Ty> onType = triple._2;
			int c = triple._3;
			return alg().TmAbs(x, onType.apply(c, ty), visitTerm(t).apply(new Tuple3<>(onVar, onType, c + 1)));
		};
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmVar(int x, int n) {
		return triple -> triple._1.apply(triple._3, x, n);
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmTag(String l, Term t,
			Ty ty) {
		return triple -> {
			BiFunction<Integer, Ty, Ty> onType = triple._2;
			int c = triple._3;
			return alg().TmTag(l, visitTerm(t).apply(triple), onType.apply(c, ty));
		};
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmInert(Ty ty) {
		return triple -> alg().TmInert(triple._2.apply(triple._3, ty));
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmAscribe(Term t, Ty ty) {
		return triple -> alg().TmAscribe(visitTerm(t).apply(triple), triple._2.apply(triple._3, ty));
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmLet(String x, Term t1,
			Term t2) {
		return triple -> alg().TmLet(x, visitTerm(t1).apply(triple),
				visitTerm(t2).apply(new Tuple3<>(triple._1, triple._2, triple._3 + 1)));
	}

	@Override
	default Function<Tuple3<VarMapper<Term>, BiFunction<Integer, Ty, Ty>, Integer>, Term> TmCase(Term t,
			List<Tuple3<String, String, Term>> cases) {
		return triple -> alg().TmCase(visitTerm(t).apply(triple), cases.stream().map(c -> new Tuple3<>(c._1, c._2, visitTerm(c._3).apply(triple))).collect(Collectors.toList()));
	}
}
