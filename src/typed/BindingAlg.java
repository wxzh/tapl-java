package typed;

import annotation.Free;

@Free
public interface BindingAlg<Bind, Ty> extends utils.BindingAlg<Bind> {
	Bind VarBind(Ty ty);
}
