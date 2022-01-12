package streams.parallel_streams;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {

    // распараллеливание iterate - плохая идея, тк полный список чисел в начале процесса свертки недоступен
    // iterate генерирует упакованные объекты, которые приходится распаковывать в обычные числа перед складыванием
    public static long getSumParallel(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() //параллельно
                .sequential() //последовательно
                .parallel() // только последняя операция определяет то, как будет выполнена вся обработка
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {

        private long total = 0;

        public void add(long value) {
            total += value;
        }

    }
}
