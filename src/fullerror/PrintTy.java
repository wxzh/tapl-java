package fullerror;

import java.util.function.Function;

import fullerror.tyalg.shared.GTyAlg;
import utils.Context;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, simplebool.PrintTy<Ty, Bind>, bot.PrintTy<Ty, Bind> {
}
