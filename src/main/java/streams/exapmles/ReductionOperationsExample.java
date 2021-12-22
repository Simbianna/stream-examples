package streams.exapmles;

import java.util.List;
import java.util.Optional;

public class ReductionOperationsExample {

    /**
     * reduce группирует элементы до тех пор, пока поток не окажется свернут в одно значение
     */
    public static int sumExample(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    /**
     * перегруженный вариант без начального значения,
     * возвращает Optional, тк возможна ситуация, в которой поток данных не содержит элементов
     */
    public static Optional<Integer> sumOverloadedExample(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::sum);
    }

    public static int multiplicationExample(List<Integer> numbers) {
        return numbers.stream()
                .reduce(1, (a, b) -> a * b);
    }

    public static Optional<Integer> maxExample(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::max);
    }

    public static Optional<Integer> minExample(List<Integer> numbers) {
        return numbers.stream()
                .reduce(Integer::min);
    }
}
