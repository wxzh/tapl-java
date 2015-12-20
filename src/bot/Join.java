package bot;

import bot.tyalg.shared.TyAlgQuery;
import utils.IJoin;

public interface Join<Ty> extends JoinMeet<Ty>, TyAlgQuery<Ty, IJoin<Ty>>, top.Join<Ty>{
}