package untyped;

public interface PrintBind<Bind> extends untyped.bindingalg.shared.BindingAlg<Bind, String> {
	default String NameBind() {
		return "";
	}
}
