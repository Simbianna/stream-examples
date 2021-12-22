package streams.exapmles;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapExample {

    /**
     * При использовании flatMap каждый из массивов отображается не в отдельный поток данных,
     * а в содержимое единого потока.
     */
    public static List<String> flatMapStringExample(String firstWord, String secondWord) {
        return Arrays.stream(new String[]{firstWord, secondWord})
                .map(word -> word.split(""))// преобразуем каждое из слов в массив его букв
                .flatMap(Arrays::stream) // схлопываем все сгенерированные потоки данных в единый поток
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * Задача: по двум заданным спискам чисел вернуть все их попарные сочетания
     */
    public static List<int[]> flatMapIntegerExample(List<Integer> firstIntList, List<Integer> secondIntList) {
        return firstIntList.stream()
                .flatMap(i -> secondIntList.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
    }
}
