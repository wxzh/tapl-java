package bot;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends typed.TyAlg<Ty> {
	Ty TyTop();
	Ty TyBot();
}
