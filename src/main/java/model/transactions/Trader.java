package model.transactions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trader {

    private String name;
    private String city;

}
