package model.transactions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

    private Trader trader;
    private int year;
    private int value;

}
