package simplebool;

import annotation.Free;

@Free
public interface TyAlg<Ty> extends typed.TyAlg<Ty>, bool.TyAlg<Ty> {
}