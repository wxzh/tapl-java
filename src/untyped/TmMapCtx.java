package untyped;

public class TmMapCtx<Term> {
	interface VarMapper<I> {
		I apply(int c, int x, int n);
	}

	VarMapper<Term> f;
	int c;
	Term t;

	public TmMapCtx<Term> setOnVar(VarMapper<Term> f) {
		this.f = f;
		return this;
	}

	public TmMapCtx<Term> setC(int c) {
		this.c = c;
		return this;
	}

	public TmMapCtx<Term> setT(Term t) {
		this.t = t;
		return this;
	}

	public Term mapVar(int x, int n) {
		return f.apply(c, x, n);
	}
}
