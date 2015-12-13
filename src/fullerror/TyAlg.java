package fullerror;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends simplebool.TyAlg<Ty>, utils.TyVarAlg<Ty> {
	Ty TyTop();
	Ty TyBot();
}
