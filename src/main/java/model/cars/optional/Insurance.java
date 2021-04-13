package model.cars.optional;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    private String name;

    public String getName() {
        return name;
    }
}
