package fullsub;

import java.util.function.Function;

import fullsub.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind>
		extends GTyAlg<Ty, Function<Context<Bind>, String>>, moreextension.PrintTy<Ty, Bind>, top.PrintTy<Ty, Bind> {
}
