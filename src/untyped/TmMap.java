package untyped;

import java.util.function.Function;

import untyped.termalg.shared.G_TermAlgTransform;


public interface TmMap<Term> extends G_TermAlgTransform<Integer, Term> {
	interface VarMapper<I> {
		I apply(int c, int x, int n);
	}

	VarMapper<Term> onVar();

	@Override
	default Function<Integer, Term> TmVar(int x, int n) {
		return c -> onVar().apply(c, x, n);
	}

	@Override
	default Function<Integer, Term> TmAbs(String x, Term t) {
		return c -> alg().TmAbs(x, visitTerm(t).apply(c+1));
	}
}

interface TermShiftAbove<Term> extends TmMap<Term> {
	int d();
	default VarMapper<Term> onVar() {
		return (c, x, n) -> x >= c ? alg().TmVar(x + d(), n + d()) : alg().TmVar(x, n + d());
	}

	default Term termShiftAbove(int c, Term t) {
		return visitTerm(t).apply(c);
	}
}

interface TermShift<Term> extends TermShiftAbove<Term> {
	default Term termShift(Term t) {
		return termShiftAbove(0, t);
	}
}

// [j -> s]
interface TermSubst<Term> extends TmMap<Term> {
	int j();
	Term s();
	TermShift<Term> termShift();

	default VarMapper<Term> onVar() {
		return (c, x, n) -> x == j() + c ? termShift().termShift(s()) : alg().TmVar(x, n);
	}
}

// Stuck here: there is no way to instantiate d with two different values, namely -1 and 1
interface TermSubstTop<Term> extends TermShift<Term>, TermSubst<Term> {
	default int d() { return -1; }
	default int j() { return 0; }

	default Term termShiftAbove(Term t) {
		return TermShift.super.termShiftAbove(t);
	}
}