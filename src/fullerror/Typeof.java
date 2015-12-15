package fullerror;

import java.util.function.Function;

import fullerror.termalg.shared.TermAlgQuery;
import fullerror.tyalg.external.TyAlgMatcher;
import fullerror.tyalg.shared.GTyAlg;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Ty, Function<Context<Bind>, Ty>>, simplebool.Typeof<Term, Ty, Bind>, bot.Typeof<Term, Ty, Bind> {
	@Override
	Subtype<Ty> subtype();
	Join<Ty, Bind> join();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	GTyAlg<Ty, Ty> tyAlg();

	@Override
	default Function<Context<Bind>, Ty> TmIf(Term t1, Term t2, Term t3) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			if (subtype().subtype(ty1, tyAlg().TyBool())) {
				Ty ty2 = visitTerm(t2).apply(ctx);
				Ty ty3 = visitTerm(t3).apply(ctx);
				return join().join(ty2, ty3);
			}
			return m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>,Ty> TmError() {
		return ctx -> tyAlg().TyBot();
	}

	@Override
	default Function<Context<Bind>, Ty> TmTry(Term t1, Term t2) {
		return ctx -> {
			Ty ty1 = visitTerm(t1).apply(ctx);
			Ty ty2 = visitTerm(t2).apply(ctx);
			return join().join(ty1, ty2);
		};
	}
}