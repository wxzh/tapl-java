package fullsimple;

import java.util.function.Function;

import fullsimple.tyalg.external.TyAlgMatcher;
import fullsimple.tyalg.shared.GTyAlg;
import utils.Context;

// TODO: reuse tyarith.PrintTy and simplebool.PrintTy
public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, simplebool.PrintTy<Ty, Bind>,
		nat.PrintTy<Ty, Bind>, record.PrintTy<Ty, Bind>, variant.PrintTy<Ty, Bind> {
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

	default Function<Context<Bind>, String> TyString() {
		return ctx -> "String";
	}
}
