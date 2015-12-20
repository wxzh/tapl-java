package fullsimple;

import java.util.function.Function;

import fullsimple.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind>
		extends GTyAlg<Ty, Function<Context<Bind>, String>>, moreextension.PrintTy<Ty, Bind>, variant.PrintTy<Ty, Bind> {
}