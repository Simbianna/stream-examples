package streams.examples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreationExample {

    public static Stream<String> ofStringsStream(String... vals) {
        return Stream.of(vals);
    }

    public static Stream<String> emptyStream() {
        return Stream.empty();
    }

    //Checks for null. Useful when val can be null
    public static Stream<String> ofNullable() {
        return Stream.ofNullable(System.getProperty("notExistingProperty"));
    }

    public static IntStream intStreamFromArrays() {
        return Arrays.stream(new int[]{1, 2, 3});
    }

    public static long countUniqueWordsFromFile() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"))) {
            uniqueWords = lines.flatMap(l -> Arrays.stream(l.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return uniqueWords;
    }

    //применяет функцию последовательно к каждому сгенерированному значению. Состояние сохраняется
    public static void infiniteStreamIterate() {
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);
    }

    //Для генерации новых значений использует лямбду. Состояние не сохраняется
    public static void infiniteStreamGenerate() {
        Stream.generate(Math::random)
                .limit(10)
                .forEach(System.out::println);
    }
}
