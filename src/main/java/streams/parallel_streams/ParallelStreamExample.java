package streams.parallel_streams;

import java.util.stream.Stream;

public class ParallelStreamExample {

    public static long getSumParallel(long n) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel() //параллельно
                .sequential() //последовательно
                .parallel() // только последняя операция определяет то, как будет выполнена вся обработка
                .reduce(0L, Long::sum);
    }

}
