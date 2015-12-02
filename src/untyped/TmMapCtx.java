package untyped;

import java.util.function.Function;

public class TmMapCtx<Term> {
	Function<Integer, Function<Integer, Function<Integer, Term>>> f;
	int c;
	Term t;

	public TmMapCtx<Term> setOnVar(Function<Integer, Function<Integer, Function<Integer, Term>>> f) {
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
		return f.apply(c).apply(x).apply(n);
	}
}
