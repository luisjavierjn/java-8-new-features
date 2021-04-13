package java8.streams;

import model.transactions.Trader;
import model.transactions.Transaction;
import model.transactions.TransactionsBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// Internal operations: filter, map, limit, sorted, distinct
// Terminal operations: forEach, collect, count, reduce (anyMatch, noneMatch, allMatch, findAny, findFirst)

public class WorkingWithStreams {

    public static void main(String[] args) {
        List<Transaction> collection = TransactionsBuilder.createTransactions();
        collection.stream().forEach( System.out::println );


        System.out.println("=================\n");
        valuesOfAllTransactionsFromTradersBasedOn(collection, "Cambridge");
        //result.stream().forEach( System.out::println );

    }

    /*Find all the transactions in the year 2011 and sort them by value (small to high)*/
    public static List<Transaction> transactionsIn2011SortedByValue(List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getYear()==2011)
                //.sorted((Transaction t1 ,Transaction t2) -> Integer.compare(t1.getValue(), t2.getValue())
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
    }

    /*What are all the unique cites where the trades work?*/
    public static Set<String> uniqueCitesWhereTradesWork(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> t.getTrader().getCity())
                .collect(Collectors.toSet());
    }

    /*Find all traders from Cambridge and sort them by name*/
    public static List<Trader> allTradersFromCambridgeSortedByName(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
    }

    /*Return a string of all traders' names sorted alphabetically*/
    public static String allTradersNameSortedAlphabetically(List<Transaction> transactions) {
        return transactions.stream()
                .map(t-> t.getTrader().getName())
                .distinct()
                .sorted()
                //.reduce("", (t1, t2) -> t1+" "+t2 ); // Inefficient solution. Use StringBuilder.
                .collect(Collectors.joining(", "));
    }

    /*Print the values of all transactions from the traders living in Cambridge*/
    public static void valuesOfAllTransactionsFromTradersBasedOn(List<Transaction> transactions, String city) {
        transactions.stream()
            .filter(t -> city.equals(t.getTrader().getCity()))
            .map(Transaction::getValue)
            .forEach(System.out::println);
    }

    /*Are any traders based in Milan?*/
    public static boolean areAnyTraderBasedOn(List<Transaction> transactions, String city) {
        return transactions.stream()
                .anyMatch(t-> city.equals(t.getTrader().getCity()));
    }

    /*What's the highest value of all the transactions?*/
    public static Optional<Integer> highestValueOfAllTransactions(List<Transaction> transactions) {
        return transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
    }

    /*Find the transaction with the smallest value*/
    public static Optional<Transaction> transactionWithSmallestValue(List<Transaction> transactions) {
        return transactions.stream()
                //.reduce((t1,t2) -> t1.getValue() > t2.getValue() ? t1 : t2);
                .min(Comparator.comparing(Transaction::getValue));
    }
}
