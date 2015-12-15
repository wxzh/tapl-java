package fullsimple;

import java.util.List;
import java.util.function.Function;

import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import library.Tuple2;
import utils.Context;

// TODO: reuse tyarith.PrintTy and simplebool.PrintTy
public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, simplebool.PrintTy<Ty, Bind>, nat.PrintTy<Ty, Bind>, record.PrintTy<Ty, Bind> {
	TyAlgMatcher<Ty, String> matcher();

	default Function<Context<Bind>, String> TyFloat() {
		return ctx -> "Float";
	}

	default Function<Context<Bind>, String> TyId(String b) {
		return ctx -> b;
	}

	default Function<Context<Bind>, String> TyUnit() {
		return ctx -> "Unit";
	}

	default Function<Context<Bind>, String> TyVariant(List<Tuple2<String, Ty>> fields) {
		return ctx -> {
			String s = TyRecord(fields).apply(ctx);
			return "<" + s.substring(1, s.length()-1) + ">";
		};
	}

	default Function<Context<Bind>, String> TyString() {
		return ctx -> "String";
	}
}
