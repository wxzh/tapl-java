package untyped;

import java.util.function.Function;

import library.Zero;
import untyped.bindingalg.shared.BindingAlgQuery;
import utils.Context;

public interface PrintBind<Bind> extends BindingAlgQuery<Bind, Function<Context<Bind>, String>> {
	default Zero<Function<Context<Bind>, String>> m() {
		return new Zero<Function<Context<Bind>, String>>() {
			public Function<Context<Bind>, String> empty() {
				return ctx -> "";
			}
		};
	}
}
