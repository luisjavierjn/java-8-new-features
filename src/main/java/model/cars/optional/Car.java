package model.cars.optional;

import java.util.Optional;

public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }


    public Car() {
        insurance = Optional.empty();
    }
    public Car(Insurance insurance) {
        this.insurance = Optional.ofNullable(insurance);
    }

}
