package utils;

import library.Zero;

public class ZeroRuntimeException<R> implements Zero<R> {
	@Override
	public R empty() {
		throw new RuntimeException();
	}
}
