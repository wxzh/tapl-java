package fullsimple;

import java.util.List;
import java.util.stream.IntStream;

import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import library.Tuple2;
import utils.ITyEqv;

public interface TyEqv<Ty> extends GTyAlg<Ty, ITyEqv<Ty>>, simplebool.TyEqv<Ty>, nat.TyEqv<Ty>, record.TyEqv<Ty> {
	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	default ITyEqv<Ty> TyUnit() {
		return ty -> matcher().TyUnit(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyFloat() {
		return ty -> matcher().TyFloat(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyId(String b1) {
		return ty -> matcher().TyId(b2 -> b1 == b2).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyVariant(List<Tuple2<String, Ty>> fields1) {
		return ty -> matcher().TyVariant(
				fields2 -> fields1.size() == fields2.size() && IntStream.range(0, fields1.size()).mapToObj(i -> {
					String l1 = fields1.get(i)._1;
					String l2 = fields2.get(i)._1;
					Ty ty1 = fields1.get(i)._2;
					Ty ty2 = fields2.get(i)._2;
					return l1.equals(l2) && visitTy(ty1).tyEqv(ty2);
				}).reduce(true, (x, y) -> x && y)).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyString() {
		return ty -> matcher().TyString(() -> true).otherwise(() -> false).visitTy(ty);
	}

	@Override
	default ITyEqv<Ty> TyNat() {
		return nat.TyEqv.super.TyNat();
	}
}