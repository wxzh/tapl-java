package rcdsubbot;

import java.util.List;

import library.Tuple2;
import library.Zero;
import rcdsubbot.tyalg.external.TyAlgMatcher;
import rcdsubbot.tyalg.shared.GTyAlg;
import rcdsubbot.tyalg.shared.TyAlgQuery;
import utils.ISubtype;

public interface Subtype<Ty> extends TyAlgQuery<Ty, ISubtype<Ty>>, bot.Subtype<Ty>, record.Subtype<Ty> {
	@Override
	TyEqv<Ty> tyEqv();

	@Override
	TyAlgMatcher<Ty, Boolean> matcher();

	@Override
	GTyAlg<Ty, Ty> alg();

	@Override
	default boolean subtype(Ty ty1, Ty ty2) {
		return bot.Subtype.super.subtype(ty1, ty2);
	}

	@Override
	default Zero<ISubtype<Ty>> m() {
		return bot.Subtype.super.m();
	}

	@Override
	default ISubtype<Ty> TyRecord(List<Tuple2<String, Ty>> fS) {
		return record.Subtype.super.TyRecord(fS);
	}
}
