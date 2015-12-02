package simplebool;

import annotation.Free;

@Free
public interface BindingAlg<Bind, Ty> extends untyped.BindingAlg<Bind> {
	Bind VarBind(Ty ty);
}
