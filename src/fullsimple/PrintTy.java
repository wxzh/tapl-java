package fullsimple;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fullsimple.tyalg.external.TyAlgMatcher;
import library.Tuple2;
import utils.Context;

// TODO: reuse tyarith.PrintTy and simplebool.PrintTy
public interface PrintTy<Ty, Bind> extends fullsimple.tyalg.shared.TyAlg<Ty, Function<Context<Bind>, String>>, simplebool.PrintTy<Ty, Bind>, utils.PrintTyVar<Ty, Bind> {
	TyAlgMatcher<Ty, String> matcher();

	default Function<Context<Bind>, String> TyFloat() {
		return ctx -> "Float";
	}

	default Function<Context<Bind>, String> TyId(String b) {
		return ctx -> b;
	}

	default Function<Context<Bind>, String> TyUnit() {
		return ctx -> "Unit";
	}

	default Function<Context<Bind>, String> TyVariant(List<Tuple2<String, Ty>> fields) {
		return ctx -> "<" + printStringTyTupleList(ctx, fields) + ">";
	}

	default Function<Context<Bind>, String> TyString() {
		return ctx -> "String";
	}

	default Function<Context<Bind>, String> TyRecord(List<Tuple2<String, Ty>> fields) {
		return ctx -> "{" + printStringTyTupleList(ctx, fields) + "}";
	}

	default Function<Context<Bind>, String> TyNat() {
		return ctx -> "Nat";
	}

	// helper
	default String printStringTyTupleList(Context<Bind> ctx, List<Tuple2<String, Ty>> ps) {
		return IntStream.range(0, ps.size()).mapToObj(i -> {
			String label = ps.get(i)._1;
			Ty ty = ps.get(i)._2;
			return String.valueOf(i + 1).equals(label) ? visitTy(ty).apply(ctx) : label + ":" + visitTy(ty).apply(ctx);
		}).collect(Collectors.joining(","));
	}

	@Override
	default Function<Context<Bind>, String> TyBool() {
		return simplebool.PrintTy.super.TyBool();
	}
}
