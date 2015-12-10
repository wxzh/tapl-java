package utils;

import java.util.function.Function;

public interface PrintTyVar<Ty, Bind> extends utils.tyvaralg.shared.TyVarAlg<Ty, Function<Context<Bind>, String>> {
	@Override
	default Function<Context<Bind>, String> TyVar(int x, int n) {
		return ctx -> ctx.length() == n ? ctx.index2Name(x) : "[bad index: " + x + "/" + n + " in " + ctx;
	}
}
