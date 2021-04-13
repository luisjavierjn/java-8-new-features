package model.transactions;

import java.util.Arrays;
import java.util.List;

public class TransactionsBuilder {

    public static List<Transaction> createTransactions(){
        Trader raoul = Trader.builder().name("Raoul").city("Cambridge").build();
        Trader mario = Trader.builder().name("Mario").city("Milan").build();
        Trader alan = Trader.builder().name("Alan").city("Cambridge").build();
        Trader brian = Trader.builder().name("Brian").city("Cambridge").build();

        List<Transaction> transactions = Arrays.asList(
            Transaction.builder().trader(brian).year(2011).value(300).build(),
            Transaction.builder().trader(raoul).year(2012).value(1000).build(),
            Transaction.builder().trader(raoul).year(2011).value(400).build(),
            Transaction.builder().trader(mario).year(2012).value(710).build(),
            Transaction.builder().trader(mario).year(2012).value(700).build(),
            Transaction.builder().trader(alan).year(2012).value(950).build()
        );

        return transactions;
    }
}
