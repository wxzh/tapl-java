package fullref;

import java.util.function.Function;

import fullref.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, fullsimple.PrintTy<Ty, Bind>, fullerror.PrintTy<Ty, Bind> {
	@Override
	default Function<Context<Bind>, String> TyRef(Ty ty) {
		return ctx -> "Ref " + visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TySource(Ty ty) {
		return ctx -> "Source " + visitTy(ty).apply(ctx);
	}

	@Override
	default Function<Context<Bind>, String> TySink(Ty ty) {
		return ctx -> "Sink " + visitTy(ty).apply(ctx);
	}
}