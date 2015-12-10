package fullsimple;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.TyAlgQuery;
import library.Tuple2;

interface TyEqv<Ty, Bind, Term> extends TyAlgQuery<Ty, Function<Ty, Boolean>>, simplebool.TyEqv<Ty, Bind, Term>, tyarith.TyEqv<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default Function<Ty, Boolean> TyVar(int x, int n) {
		return ty -> matcher()
				.TyVar(y -> m -> x == y)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyUnit() {
		return ty -> matcher()
				.TyUnit(() -> true).otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyFloat() {
		return ty -> matcher()
				.TyFloat(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyRecord(List<Tuple2<String, Ty>> fields1) {
		return ty -> matcher()
				.TyRecord(fields2 -> fields1.size() == fields2.size() && fields1.stream()
						.allMatch(pr1 -> fields2.stream()
								.anyMatch(pr2 -> pr2._1.equals(pr1._1) && visitTy(pr1._2).apply(pr2._2))))
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyId(String b1) {
		return ty -> matcher()
				.TyId(b2 -> b1 == b2)
				.otherwise(() -> false)
				.visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyVariant(List<Tuple2<String, Ty>> fields1) {
		return ty -> matcher()
				.TyVariant( fields2 -> fields1.size() == fields2.size() && IntStream.range(0, fields1.size()).mapToObj(i -> {
					String l1 = fields1.get(i)._1;
					String l2 = fields2.get(i)._1;
					Ty ty1 = fields1.get(i)._2;
					Ty ty2 = fields2.get(i)._2;
					return l1.equals(l2) && visitTy(ty1).apply(ty2);
				}).reduce(true, (x, y) -> x && y))
				.otherwise(() -> false).visitTy(ty);
	}

	@Override
	default Function<Ty, Boolean> TyString() {
		return ty -> matcher()
				.TyString(() -> true)
				.otherwise(() -> false)
				.visitTy(ty);
	}
}
