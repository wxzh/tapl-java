package record;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import library.Tuple2;
import record.termalg.shared.GTermAlg;
import record.tyalg.external.TyAlgMatcher;
import record.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends GTermAlg<Term, Function<Context<Bind>, Ty>>, typed.Typeof<Term, Ty, Bind> {
	@Override
	GTyAlg<Ty, Ty> tyAlg();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();

	@Override
	default Function<Context<Bind>, Ty> TmRecord(List<Tuple2<String, Term>> fields) {
		return ctx -> tyAlg().TyRecord(fields.stream().map(pr -> new Tuple2<>(pr._1, visitTerm(pr._2).apply(ctx)))
				.collect(Collectors.toList()));
	}

	@Override
	default Function<Context<Bind>, Ty> TmProj(Term t, String l) {
		return ctx -> {
			Ty tyT = visitTerm(t).apply(ctx);
			return tyMatcher()
					.TyRecord(fieldTys -> fieldTys.stream().filter(pr -> pr._1.equals(l)).findFirst().map(pr -> pr._2)
							.orElseGet(() -> m().empty().apply(ctx)))
					.otherwise(() -> m().empty().apply(ctx)).visitTy(tyT);
		};
	}
}