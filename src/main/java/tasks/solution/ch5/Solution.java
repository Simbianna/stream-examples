package tasks.solution.ch5;

import tasks.data.ch5.Trader;
import tasks.data.ch5.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Solution {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        List<Transaction> task1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(task1);

        // Query 2: What are all the unique cities where the traders work?
        List<String> task2 = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(task2);

        // Query 3: Find all traders from Cambridge and sort them by name.
        List<Trader> task3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(task3);

        // Query 4: Return a string of all traders' names sorted alphabetically.
        String task4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(task4);

        // Query 5: Are there any trader based in Milan?
        boolean task5 = transactions
                .stream()
                .anyMatch(t -> t.getTrader().getName().equals("Milan"));
        System.out.println(task5);

        // Query 6: Print all transactions' values from the traders living in Cambridge.
       transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // Query 7: What's the highest value in all the transactions?
        int task7 = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();

        // Find the transaction with the smallest value
        Optional<Transaction> smallestTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));
        // Here I cheat a bit by converting the found Transaction (if any) to a String
        // so that I can use a default String if no transactions are found (i.e. the Stream is empty).
        System.out.println(smallestTransaction.map(String::valueOf).orElse("No transactions found"));

    }

}
