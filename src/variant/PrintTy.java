package variant;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import library.Tuple2;
import utils.Context;
import variant.tyalg.shared.GTyAlg;

public interface PrintTy<Ty, Bind> extends GTyAlg<Ty, Function<Context<Bind>, String>>, typed.PrintTy<Ty, Bind> {
	@Override
	default Function<Context<Bind>, String> TyVariant(List<Tuple2<String, Ty>> fields) {
		return ctx -> "<" + IntStream.range(0, fields.size()).mapToObj(i -> {
			String label = fields.get(i)._1;
			Ty ty = fields.get(i)._2;
			return String.valueOf(i + 1).equals(label) ? visitTy(ty).apply(ctx) : label + ":" + visitTy(ty).apply(ctx);
		}).collect(Collectors.joining(",")) + ">";
	}
}