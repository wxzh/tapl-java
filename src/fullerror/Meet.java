package fullerror;

import fullerror.tyalg.shared.TyAlgQuery;
import utils.IMeet;

public interface Meet<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, IMeet<Ty>>, bot.Meet<Ty> {
}