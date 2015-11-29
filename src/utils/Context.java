package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import library.Tuple2;

interface Binding {
}

class NameBind implements Binding {
}

public class Context {
	private List<Tuple2<String, Binding>> binds;

	public int length() {
		return binds.size();
	}

	public Context() {
		this.binds = new ArrayList<>();
	}

	public Context(List<Tuple2<String, Binding>> binds) {
		this.binds = new ArrayList<>(binds);
	}

	public Context addBinding(String name, Binding bind) {
		List<Tuple2<String, Binding>> binds2 = new ArrayList<>(binds);
		binds2.add(0, new Tuple2<>(name, bind));
		return new Context(binds2);
	}

	public Context addName(String name) {
		return addBinding(name, new NameBind());
	}

	public boolean isNameBound(String s) {
		return binds.stream().filter(b -> b._1.equals(s)).findFirst().isPresent();
	}

	public Tuple2<Context, String> pickFreshName(String n) {
		return isNameBound(n) ? pickFreshName(n + "_") : new Tuple2<>(addBinding(n, new NameBind()), n);
	}

	public String index2Name(int i) {
		return binds.get(i)._1;
	}

	public int name2Index(String s) throws Exception {
		return IntStream.range(0, binds.size()).filter(i -> binds.get(i)._1.equals(s)).findFirst()
				.orElseThrow(() -> new Exception("identifier " + s + " is unbound"));
	}

	public Binding getBinding(int i) {
		return binds.get(i)._2;
	}

	@Override
	public String toString() {
		return binds.toString();
	}
}