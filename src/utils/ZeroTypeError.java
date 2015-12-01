package utils;

import library.Zero;

public class ZeroTypeError<R> implements Zero<R>{
	public R empty() {
		throw new TypeError();
	}
}
