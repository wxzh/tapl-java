package utils;

import library.Zero;

public class ZeroRuntimException<R> implements Zero<R> {
	@Override
	public R empty() {
		throw new RuntimeException();
	}
}
