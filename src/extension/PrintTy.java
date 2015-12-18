package extension;

import java.util.function.Function;

import extension.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, tyarith.PrintTy<Ty, Bind>, record.PrintTy<Ty, Bind> {
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
