package java8.optionals;

import model.cars.old.OldCar;
import model.cars.old.OldInsurance;
import model.cars.old.OldPerson;
import model.cars.optional.Car;
import model.cars.optional.Insurance;
import model.cars.optional.Person;

import java.util.Optional;

// Optionals are a kind of streams with 0 or 1 element
// Unwrapping optionals
public class UsingOptionals {

    public static String getCarInsuranceName(OldPerson person) {
        if (person != null) {
            OldCar car = person.getCar();
            if (car != null) {
                OldInsurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }
        return null;
    }

    public static String getCarInsuranceNameWithDefault(OldPerson person) {
        if (person == null) {
            return "Unknown";
        }
        OldCar car = person.getCar();
        if (car == null) {
            return "Unknown";
        }
        OldInsurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }
        return insurance.getName();
    }

    public static String getCarInsuranceNameWithDefault(Optional<Person> optPerson) {
        return optPerson.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                //.orElse("Unknown"); // Basic unwrapper.
                //.orElse(callFromAnotherComplexDatabase()); // Method call unwrapper.
                .orElseGet(() -> callFromAnotherComplexDatabase()); // Method call unwrapper. LAZY CALL.
    }

    public static String callFromAnotherComplexDatabase() {
        System.out.println("Calling database...");
        return "Fresh value";
    }

    public static void main(String[] args) {

        Insurance insurance = new Insurance("Basic insurance");
        Car batmobile = new Car();
        //Car batmobile = new Car(insurance);
        Person bruceWayne = new Person(batmobile);

        Optional<Person> batman = Optional.of(bruceWayne);

        System.out.println("INSURANCE: " + getCarInsuranceNameWithDefault(batman) );

    }

}
