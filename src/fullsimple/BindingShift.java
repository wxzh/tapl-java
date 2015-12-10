package fullsimple;

import java.util.Optional;
import java.util.function.Function;

import fullsimple.bindingalg.shared.G_BindingAlgTransform;

public interface BindingShift<Bind, Term, Ty>
		extends G_BindingAlgTransform<Integer, Bind, Term, Ty>, TypeShiftAndSubst<Ty> {
	@Override
	default Function<Integer, Bind> VarBind(Ty ty) {
		return d -> alg().VarBind(typeShift(d, ty));
	}

	@Override
	default Function<Integer, Bind> TmAbbBind(Term t, Optional<Ty> tyOpt) {
		return d -> alg().TmAbbBind(t, tyOpt.map(ty -> typeShift(d, ty)));
	}

	@Override
	default Function<Integer, Bind> TyAbbBind(Ty ty) {
		return d -> alg().TyAbbBind(typeShift(d, ty));
	}
}