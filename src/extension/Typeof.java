package extension;

import java.util.List;
import java.util.function.Function;

import extension.termalg.shared.TermAlgQuery;
import extension.tyalg.external.TyAlgMatcher;
import extension.tyalg.shared.GTyAlg;
import library.Tuple2;
import utils.Context;

public interface Typeof<Term, Ty, Bind> extends TermAlgQuery<Term, Function<Context<Bind>, Ty>>,
		tyarith.Typeof<Term, Ty, Bind>, record.Typeof<Term, Ty, Bind> {
	@Override
	TyEqv<Ty> tyEqv();
	@Override
	TyAlgMatcher<Ty, Ty> tyMatcher();
	@Override
	GTyAlg<Ty, Ty> tyAlg();

	@Override
	default Function<Context<Bind>, Ty> TmTimesFloat(Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			Ty tyT2 = visitTerm(t2).apply(ctx);
			Ty tyFloat = tyAlg().TyFloat();
			return tyEqv().visitTy(tyT1).tyEqv(tyFloat) &&
					tyEqv().visitTy(tyT2).tyEqv(tyFloat)
					? tyFloat
					: m().empty().apply(ctx);
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmFloat(float p1) {
		return ctx -> tyAlg().TyFloat();
	}

	@Override
	default Function<Context<Bind>, Ty> TmString(String p1) {
		return ctx -> tyAlg().TyString();
	}

	@Override
	default Function<Context<Bind>, Ty> TmLet(String x, Term t1, Term t2) {
		return ctx -> {
			Ty tyT1 = visitTerm(t1).apply(ctx);
			return visitTerm(t2).apply(ctx.addBinding(x, bindAlg().VarBind(tyT1)));
		};
	}

	@Override
	default Function<Context<Bind>, Ty> TmRecord(List<Tuple2<String, Term>> fields) {
		return record.Typeof.super.TmRecord(fields);
	}

	@Override
	default Function<Context<Bind>, Ty> TmProj(Term t, String l) {
		return record.Typeof.super.TmProj(t, l);
	}
}