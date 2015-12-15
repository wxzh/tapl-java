package utils;

import java.util.function.Function;

import library.Zero;
import utils.bindingalg.shared.BindingAlgQuery;

public interface PrintBind<Bind> extends BindingAlgQuery<Bind, Function<Context<Bind>, String>> {
	@Override
	default Zero<Function<Context<Bind>, String>> m() {
		return () -> ctx -> "";
	}
}
