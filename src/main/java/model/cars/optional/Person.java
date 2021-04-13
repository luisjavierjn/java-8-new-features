package model.cars.optional;

import java.util.Optional;


public class Person {

    private Optional<Car> car;
    public Optional<Car> getCar() {
        return car;
    }


    public Person() {
        car = Optional.empty();
    }
    public Person(Car car) {
        this.car = Optional.ofNullable(car);
    }
}
