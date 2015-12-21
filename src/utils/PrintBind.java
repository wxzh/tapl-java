package utils;

import library.Zero;
import utils.bindingalg.shared.BindingAlgQuery;

public interface PrintBind<Bind> extends BindingAlgQuery<Bind, IPrint<Bind>> {
	@Override
	default Zero<IPrint<Bind>> m() {
		return () -> ctx -> "";
	}
}
