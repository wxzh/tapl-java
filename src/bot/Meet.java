package bot;

import bot.tyalg.shared.TyAlgQuery;
import library.Zero;
import utils.IMeet;

public interface Meet<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, IMeet<Ty>>, top.Meet<Ty> {
	@Override
	default Zero<IMeet<Ty>> m() {
		return () -> ty -> alg().TyBot();
	}
}