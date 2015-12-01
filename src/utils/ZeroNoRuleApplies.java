package utils;

import library.Zero;

public class ZeroNoRuleApplies<R> implements Zero<R>{
	@Override
	public R empty() {
		throw new NoRuleApplies();
	}
}
