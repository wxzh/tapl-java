package utils;

import library.Zero;

public class ZeroFalse implements Zero<Boolean> {
	@Override
	public Boolean empty() {
		return false;
	}
}
