package java8.streams;

import model.transactions.Transaction;
import model.transactions.TransactionsBuilder;

import java.util.*;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

// Reducing and summarizing
// Grouping
// Partitioning
public class CollectingDataWithStreams {

    public static void main(String[] args) {
        List<Transaction> transactions = TransactionsBuilder.createTransactions();
        transactions.stream().forEach(System.out::println);
        System.out.println("======================\n");

        // Reducing and summarizing
        // ========================
        int totalAmountOfTransactions1 = transactions.stream().collect(summingInt(Transaction::getValue));
        int totalAmountOfTransactions2 = transactions.stream().mapToInt(Transaction::getValue).sum();
        Double averageAmountOfTransactions = transactions.stream().collect(averagingInt(Transaction::getValue));

        IntSummaryStatistics transactionStatistics = transactions.stream().collect(summarizingInt(Transaction::getValue));
        System.out.println(transactionStatistics);

        // Grouping
        // ========
        Map<String, List<Transaction>> transactionsByCity = transactions.stream()
                .collect(groupingBy(t -> t.getTrader().getCity()));

        Map<String, List<Transaction>> transactionsByCity2 = oldStyleGrouping(transactions);

        for(String city : transactionsByCity.keySet()) {
            System.out.println("*****"+city+"*********");
            System.out.println(transactionsByCity.get(city) +"\n");
        }

        // Partitioning
        // ============
        Map<Boolean, List<Transaction>> highValueTransaction = transactions.stream()
                .collect(partitioningBy(t -> t.getValue() > 900));

        System.out.println( highValueTransaction.get(false) );
    }

    public static Map<String, List<Transaction>> oldStyleGrouping(List<Transaction> transactions) {
        Map<String, List<Transaction>> transactionsByCity = new HashMap<>();
        for( Transaction t: transactions ) {

            String city = t.getTrader().getCity();
            List<Transaction> transactionsInACity = transactionsByCity.get(city);

            if(transactionsInACity == null ) {
                transactionsInACity = new ArrayList<>();
                transactionsByCity.put(city, transactionsInACity);
            }

            transactionsInACity.add(t);
        }
        return transactionsByCity;
    }

}
