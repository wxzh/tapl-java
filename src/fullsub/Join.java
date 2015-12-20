package fullsub;

import fullsub.tyalg.shared.TyAlgQuery;
import library.Zero;
import utils.IJoin;

public interface Join<Ty> extends TyAlgQuery<Ty, IJoin<Ty>>, JoinMeet<Ty>, top.Join<Ty>, record.Join<Ty> {
	@Override
	default Zero<IJoin<Ty>> m() {
		return top.Join.super.m();
	}
}
