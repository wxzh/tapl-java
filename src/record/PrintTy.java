package record;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import library.Tuple2;
import utils.Context;

public interface PrintTy<Ty, Bind> extends record.tyalg.shared.TyAlg<Ty, Function<Context<Bind>, String>> {
	default Function<Context<Bind>, String> TyRecord(List<Tuple2<String, Ty>> fields) {
		return ctx -> "{" + IntStream.range(0, fields.size()).mapToObj(i -> {
			String label = fields.get(i)._1;
			Ty ty = fields.get(i)._2;
			return String.valueOf(i + 1).equals(label) ? visitTy(ty).apply(ctx) : label + ":" + visitTy(ty).apply(ctx);
		}).collect(Collectors.joining(",")) + "}";
	}
}